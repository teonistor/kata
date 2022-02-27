<!--suppress HtmlUnknownTag, CheckEmptyScriptTag, HtmlUnknownAttribute, XmlUnboundNsPrefix -->
<template>
  <v-container fluid>
    <h2>The "Monster" Diagram of modular times tables</h2>
    <v-row>
      <v-col cols="12" lg="4">
        <!--        <v-expansion-panels>-->
        <!--          <v-expansion-panel open hover focusable >-->
        <!--            <v-expansion-panel-header>-->
        <!--              Optoins-->
        <!--            </v-expansion-panel-header>-->
        <!--            <v-expansion-panel-content>-->
        <p>
          <v-btn href="https://github.com/teonistor/kata/tree/modular-times-table" target="_blank">Source code</v-btn>
          &emsp;
          <v-btn href="https://www.youtube.com/watch?v=6ZrO90AI0c8" target="_blank">Source of inspiration</v-btn>
        </p>
        <hr>

        <v-slider v-model="size"
                  :min="100"
                  :max="1500"
                  label="Picture size" >
          <template v-slot:append>
            <v-text-field v-model="size"
                          :min="100"
                          class="mt-0 pt-0"
                          type="number"
                          style="width: 60px"/>
          </template>
        </v-slider>

        <v-slider v-model="n"
                  :min="1"
                  :max="Math.max(255, n * 1.1)"
                  label="modulus" >
          <template v-slot:append>
            <v-text-field v-model="n"
                          :min="1"
                          class="mt-0 pt-0"
                          type="number"
                          style="width: 60px"/>
          </template>
        </v-slider>

        <v-slider v-model="m"
                  :min="2"
                  :max="Math.max(255, m * 1.1)"
                  label="multiplier" >
          <template v-slot:append>
            <v-text-field v-model="m"
                          :min="2"
                          class="mt-0 pt-0"
                          type="number"
                          style="width: 60px"/>
          </template>
        </v-slider>

        <hr>
        <v-switch label="Show outer circle" v-model="showCircle" style="display:inline-block"/>&emsp;
        <v-switch label="Show outer points" v-model="showPoints" style="display:inline-block"/>&emsp;
        <v-switch label="Show arrows" v-model="showArrows" style="display:inline-block"/>
        <!-- Other toggles: Display numbers on the rim... -->
        <hr>

        Show segment length in color as:&emsp;
        <v-switch label="Hue" v-model="lengthAsHue" style="display:inline-block" />&emsp;
        <v-switch label="Lightness" v-model="lengthAsLightness" style="display:inline-block" color="white" />&emsp;
        <v-switch label="R" v-model="lengthAsR" style="display:inline-block" color="red" />&emsp;
        <v-switch label="G" v-model="lengthAsG" style="display:inline-block" color="green" />&emsp;
        <v-switch label="B" v-model="lengthAsB" style="display:inline-block" color="blue" />
        <br>
        Show different loops in color as:&emsp;
        <v-switch label="Hue" v-model="loopsAsHue" style="display:inline-block" />&emsp;
        <v-switch label="Lightness" v-model="loopsAsLightness" style="display:inline-block" color="white" />&emsp;
        <v-switch label="R" v-model="loopsAsR" style="display:inline-block" color="red" />&emsp;
        <v-switch label="G" v-model="loopsAsG" style="display:inline-block" color="green" />&emsp;
        <v-switch label="B" v-model="loopsAsB" style="display:inline-block" color="blue" />
        <br>
        <small>(You can use either HSL or RGB at a time, and you can only use one component to mean one thing)</small>
        <hr>

        <!--            </v-expansion-panel-content>-->
        <!--          </v-expansion-panel>-->
        <!--        </v-expansion-panels>-->
      </v-col>
      <v-col cols="12" lg="8">

        <svg :width="size * 2 + 4"
             :height="size * 2 + 4"
             :viewBox="'-' + (size + 2) + ' -' + (size + 2) + ' ' + (size * 2 + 4) + ' ' + (size * 2 + 4)">
          <g v-for="line in lines"
             v-let="color = computeColor(line)">
            <line :x1="line.x1"
                  :y1="line.y1"
                  :x2="line.x2"
                  :y2="line.y2"
                  :style="{stroke: color, 'stroke-width': '1' }" />
            <polygon v-if="showArrows"
                     :points="computeArrow(line)"
                     :style="{fill: color, stroke: 'none' }" />
          </g>
          <circle v-if="showCircle"
                  cx="0" cy="0"
                  stroke="rgb(180,180,180)" fill="none"
                  stroke-width="0.5"
                  :r="size" />
          <circle v-if="showPoints"
                  v-for="point in points"
                  :cx="point.x"
                  :cy="point.y"
                  stroke="none"
                  fill="rgb(210,210,210)"
                  r="2" />
        </svg>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>

  export default {

    props: [],

    data: () => ({
      size: 300,
      n: 9,
      m: 2,
      
      showCircle: true,
      showPoints: true,
      showArrows: false,
      
      lengthAsHue: false,
      lengthAsLightness: true,
      lengthAsR: false,
      lengthAsG: false,
      lengthAsB: false,
      loopsAsHue: true,
      loopsAsLightness: false,
      loopsAsR: false,
      loopsAsG: false,
      loopsAsB: false
    }),

    computed: {
      points() {
        let points = [];
        for (let t = 0; t < this.n; t++) {
          let r = Math.PI * 2 * t / this.n;
          points.push({
            // Swap trig to place zero at the top; also SVG coordinates aren't like in math class
            x: -Math.sin(r) * this.size,
            y: -Math.cos(r) * this.size
          });
        }
        return points;
      },

      pairs() {
        let pairs = [];
        for (let i = 0; i < this.n; i++) {
          pairs[i] = i * this.m % this.n;
        }
        return pairs;
      },

      loopMins() {
        // TODO These variable names are very confusing
        let points = new Set(this.pairs);
        let loopMins = [];

        while (points.size > 0) {
          // Why is this so cumbersome? Why JS why
          let current = points.entries().next().value[0];
          let min = current;
          let visited = new Set([current]);
          points.delete(current);
          while(!visited.has(this.pairs[current])) {
            current = this.pairs[current];
            min = Math.min(min, current);
            visited.add(current);
            points.delete(current);
          }
          visited.forEach(i => loopMins[i] = min);
        }

        return loopMins;
      },

      loopColors() {
        let distinctLoops = new Set(this.loopMins);
        let loopColors = [];
        distinctLoops.forEach((loop, i) =>
          loopColors[loop] = i / distinctLoops.size);
        return loopColors;
      },

      lines() {
        return this.pairs.map((to, from) => ({
          x1: this.points[from].x,
          x2: this.points[to].x,
          y1: this.points[from].y,
          y2: this.points[to].y,
          to
        }));
      }
    },

    methods: {
      computeColor(line) {
        if (this.lengthAsHue || this.lengthAsLightness || this.loopsAsHue || this.loopsAsLightness) {
          let hue = (this.lengthAsHue && Math.round(this.computeLength(line) * 180 / this.size))
                 || (this.loopsAsHue && Math.round(this.loopColors[this.loopMins[line.to]] * 360))
                 || 25;
          let lightness = (this.lengthAsLightness && 10 + Math.round(this.computeLength(line) * 40 / this.size))
                       || (this.loopsAsLightness && 10 + Math.round(this.loopColors[this.loopMins[line.to]] * 80))
                       || 50;
          return 'hsl(' + hue + ',80%,' + lightness + '%)';
        }

        if (this.lengthAsR || this.lengthAsG || this.lengthAsB || this.loopsAsR || this.loopsAsG || this.loopsAsB) {
          let r = (this.lengthAsR && Math.round(this.computeLength(line) * 127.5 / this.size))
               || (this.loopsAsR && Math.round(this.loopColors[this.loopMins[line.to]] * 255))
               || 127;
          let g = (this.lengthAsG && Math.round(this.computeLength(line) * 127.5 / this.size))
               || (this.loopsAsG && Math.round(this.loopColors[this.loopMins[line.to]] * 255))
               || 127;
          let b = (this.lengthAsB && Math.round(this.computeLength(line) * 127.5 / this.size))
               || (this.loopsAsB && Math.round(this.loopColors[this.loopMins[line.to]] * 255))
               || 127;
          return 'rgb(' + r + ',' + g + ',' + b + ')';
        }

        return 'rgb(127, 127, 127)';
      },

      computeLength(line) {
        let dx = line.x1 - line.x2;
        let dy = line.y1 - line.y2;
        return Math.sqrt(dx * dx + dy * dy);
      },

      computeArrow(line) {
        const arrowSizeSq = this.size * this.size / 6400;

        let centreX = (line.x1 + line.x2) / 2;
        let centreY = (line.y1 + line.y2) / 2;

        let dx = line.x2 - line.x1;
        let dy = line.y2 - line.y1;
        let scale = Math.sqrt(arrowSizeSq / (dx * dx + dy * dy));
        dx *= scale;
        dy *= scale;

        return (centreX + dx * 2) + ',' + (centreY + dy * 2) + ' '
             + (centreX + dy)     + ',' + (centreY - dx)     + ' '
             + (centreX - dy)     + ',' + (centreY + dx);
      }
    },

    watch: {

      // The next 10 watches just enforce the rule expressed in one line of human language at the bottom of the color toggles

      lengthAsHue(value) {
        if (value) {
          this.loopsAsHue = false;
          this.loopsAsR = false;
          this.loopsAsG = false;
          this.loopsAsB = false;
          this.lengthAsR = false;
          this.lengthAsG = false;
          this.lengthAsB = false;
        }
      },

      lengthAsLightness(value) {
        if (value) {
          this.loopsAsLightness = false;
          this.loopsAsR = false;
          this.loopsAsG = false;
          this.loopsAsB = false;
          this.lengthAsR = false;
          this.lengthAsG = false;
          this.lengthAsB = false;
        }
      },

      lengthAsR(value) {
        if (value) {
          this.loopsAsR = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      },

      lengthAsG(value) {
        if (value) {
          this.loopsAsG = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      },

      lengthAsB(value) {
        if (value) {
          this.loopsAsB = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      },

      loopsAsHue(value) {
        if (value) {
          this.lengthAsHue = false;
          this.loopsAsR = false;
          this.loopsAsG = false;
          this.loopsAsB = false;
          this.lengthAsR = false;
          this.lengthAsG = false;
          this.lengthAsB = false;
        }
      },

      loopsAsLightness(value) {
        if (value) {
          this.lengthAsLightness = false;
          this.loopsAsR = false;
          this.loopsAsG = false;
          this.loopsAsB = false;
          this.lengthAsR = false;
          this.lengthAsG = false;
          this.lengthAsB = false;
        }
      },

      loopsAsR(value) {
        if (value) {
          this.lengthAsR = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      },

      loopsAsG(value) {
        if (value) {
          this.lengthAsG = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      },

      loopsAsB(value) {
        if (value) {
          this.lengthAsB = false;
          this.lengthAsHue = false;
          this.lengthAsLightness = false;
          this.loopsAsHue = false;
          this.loopsAsLightness = false;
        }
      }

    },

    mounted () {

    }
  }
</script>

<style>

</style>
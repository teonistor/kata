<!--suppress HtmlUnknownTag, CheckEmptyScriptTag, HtmlUnknownAttribute, XmlUnboundNsPrefix -->
<template>
  <v-container fluid>
    <h2>The "Monster" Diagram of modular times tables</h2>
    <p>
<!--      <v-btn href>Source code</v-btn>-->
      &nbsp;
<!--      <v-btn href>Source of inspiration</v-btn>-->
    </p>
    <p>
      <v-slider v-model="n"
                :min="1"
                :max="255"
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
                :max="255"
                label="multiplier" >
        <template v-slot:append>
          <v-text-field v-model="m"
                        :min="2"
                        class="mt-0 pt-0"
                        type="number"
                        style="width: 60px"/>
        </template>
      </v-slider>
    </p>

<!--    <v-row>-->
<!--      <v-col cols="12" sm="4"></v-col>-->
<!--      -->
<!--    </v-row>-->
    <p>
      Show segment length in color as:&emsp;&emsp;
      <v-switch label="RGB" v-model="lengthAsRgb" style="display:inline-block" />&emsp;&emsp;
      <v-switch label="HSL" v-model="lengthAsHsl" style="display:inline-block" />
      <br>
      Show different loops in color as:&emsp;&emsp;
      <v-switch label="RGB" v-model="loopAsRgb" style="display:inline-block" />&emsp;&emsp;
      <v-switch label="HSL" v-model="loopAsHsl" style="display:inline-block" />

<!--      <v-radio-group v-model="radioGroup">-->
<!--        <v-radio-->
<!--              v-for="n in 3"-->
<!--              :key="n"-->
<!--              :label="`Radio ${n}`"-->
<!--              :value="n"-->
<!--        ></v-radio>-->
<!--      </v-radio-group>-->
    </p>

    <svg :width="size * 2"
         :height="size * 2"
         :viewBox="'-' + size + ' -' + size + ' ' + size * 2 + ' ' + size * 2" >
      <line v-for="line in lines"
            :x1="line.x1"
            :y1="line.y1"
            :x2="line.x2"
            :y2="line.y2"
            :style="{stroke: 'hsl(' + line.color + ',50%,50%)', 'stroke-width': '1' }" />
<!--    :style="{stroke: 'rgb(' + line.color + ',50,25)', 'stroke-width': '1' }" />-->
    </svg>

  </v-container>
</template>

<script>

  export default {

    props: [],

    data: () => ({
      size: 300,
      n: 9,
      m: 2,
      lengthAsRgb: false,
      lengthAsHsl: true,
      loopAsRgb: false,
      loopAsHsl: false
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
          loopColors[loop] = Math.round(360 * i / distinctLoops.size));
        return loopColors;
      },

      lines() {
        return this.pairs.map((to, from) => {
          let line = {
            x1: this.points[from].x,
            x2: this.points[to].x,
            y1: this.points[from].y,
            y2: this.points[to].y };
          // line.color = Math.round(this.computeLength(line) * 127.5 / this.size);
          line.color = this.loopColors[this.loopMins[to]];
          return line;
        });
      }
    },

    methods: {
      computeLength(line) {
        let dx = line.x1 - line.x2;
        let dy = line.y1 - line.y2;
        return Math.sqrt(dx * dx + dy * dy);
      }
    },

    watch: {},

    mounted () {

    }
  }
</script>

<style>

</style>
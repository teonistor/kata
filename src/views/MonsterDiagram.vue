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
                label="n" >
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
                label="m" >
        <template v-slot:append>
          <v-text-field v-model="m"
                        :min="2"
                        class="mt-0 pt-0"
                        type="number"
                        style="width: 60px"/>
        </template>
      </v-slider>
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
      n: 9,
      m: 2,

      size: 300
    }),

    computed: {
      points() {
        let points = [];
        for (let t = 0; t < this.n; t++) {
          let r = Math.PI * 2 * t / this.n;
          points.push({
            // Swap trig to start at the top; also SVG coordinates aren't like in math class
            x: -Math.sin(r) * this.size,
            y: -Math.cos(r) * this.size
          });
        }
        return points;
      },

      pairs() {
        let points = new Set(this.points);
        let pairs = [];
        // TODO let loops = [];

        for (let i = 0; i < this.n; i++) {
          pairs[i] = i * this.m % this.n;
        }

        return pairs;
      },

      lines() {
        return this.pairs.map((to, from) => {
          let line = {
            x1: this.points[from].x,
            x2: this.points[to].x,
            y1: this.points[from].y,
            y2: this.points[to].y };
          line.color = Math.round(this.computeLength(line) * 127.5 / this.size);
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

    mounted () {

    }
  }
</script>

<style>

</style>
package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _14Test extends AdventOfCodeTestBase {
  testAndRun(_14,
    "498,4 -> 498,6 -> 496,6\n503,4 -> 502,4 -> 502,9 -> 494,9",
    24,
    93,
    "512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n511,86 -> 515,86\n485,22 -> 489,22\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n509,150 -> 513,150\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n494,24 -> 498,24\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n497,154 -> 501,154\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n523,80 -> 527,80\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n505,17 -> 510,17\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n498,17 -> 503,17\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n509,31 -> 509,32 -> 513,32 -> 513,31\n504,90 -> 504,91 -> 513,91 -> 513,90\n493,48 -> 493,49 -> 512,49 -> 512,48\n479,26 -> 483,26\n488,24 -> 492,24\n512,152 -> 516,152\n491,26 -> 495,26\n493,48 -> 493,49 -> 512,49 -> 512,48\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n517,80 -> 521,80\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n514,83 -> 518,83\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n529,86 -> 533,86\n526,83 -> 530,83\n497,13 -> 502,13\n516,108 -> 516,109 -> 520,109 -> 520,108\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n503,154 -> 507,154\n494,15 -> 499,15\n520,77 -> 524,77\n506,152 -> 510,152\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n493,48 -> 493,49 -> 512,49 -> 512,48\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n516,108 -> 516,109 -> 520,109 -> 520,108\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n488,20 -> 492,20\n500,152 -> 504,152\n509,31 -> 509,32 -> 513,32 -> 513,31\n509,154 -> 513,154\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n523,86 -> 527,86\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n491,22 -> 495,22\n497,26 -> 501,26\n506,148 -> 510,148\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n494,157 -> 494,159 -> 491,159 -> 491,167 -> 503,167 -> 503,159 -> 499,159 -> 499,157\n516,108 -> 516,109 -> 520,109 -> 520,108\n501,15 -> 506,15\n516,125 -> 516,129 -> 515,129 -> 515,136 -> 526,136 -> 526,129 -> 519,129 -> 519,125\n491,17 -> 496,17\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n512,139 -> 512,142 -> 509,142 -> 509,145 -> 521,145 -> 521,142 -> 517,142 -> 517,139\n504,90 -> 504,91 -> 513,91 -> 513,90\n503,45 -> 503,41 -> 503,45 -> 505,45 -> 505,43 -> 505,45 -> 507,45 -> 507,37 -> 507,45 -> 509,45 -> 509,35 -> 509,45 -> 511,45 -> 511,37 -> 511,45\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62\n482,24 -> 486,24\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n509,31 -> 509,32 -> 513,32 -> 513,31\n516,65 -> 516,69 -> 508,69 -> 508,74 -> 521,74 -> 521,69 -> 520,69 -> 520,65\n512,122 -> 512,118 -> 512,122 -> 514,122 -> 514,120 -> 514,122 -> 516,122 -> 516,121 -> 516,122\n515,154 -> 519,154\n517,86 -> 521,86\n485,26 -> 489,26\n504,90 -> 504,91 -> 513,91 -> 513,90\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n510,104 -> 510,96 -> 510,104 -> 512,104 -> 512,101 -> 512,104 -> 514,104 -> 514,100 -> 514,104 -> 516,104 -> 516,103 -> 516,104\n503,150 -> 507,150\n520,83 -> 524,83\n508,62 -> 508,56 -> 508,62 -> 510,62 -> 510,52 -> 510,62 -> 512,62 -> 512,60 -> 512,62 -> 514,62 -> 514,58 -> 514,62 -> 516,62 -> 516,55 -> 516,62"
  )
}
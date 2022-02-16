package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.AdventOfCodeTestBase
import io.github.teonistor.adventofcode.y2021._19.{AffixResult, TurnAndAffixResult}

class _19Test extends AdventOfCodeTestBase {
  testAndRun(19, _19._1, _19._2,
    "--- scanner 0 ---\n404,-588,-901\n528,-643,409\n-838,591,734\n390,-675,-793\n-537,-823,-458\n-485,-357,347\n-345,-311,381\n-661,-816,-575\n-876,649,763\n-618,-824,-621\n553,345,-567\n474,580,667\n-447,-329,318\n-584,868,-557\n544,-627,-890\n564,392,-477\n455,729,728\n-892,524,684\n-689,845,-530\n423,-701,434\n7,-33,-71\n630,319,-379\n443,580,662\n-789,900,-551\n459,-707,401\n\n--- scanner 1 ---\n686,422,578\n605,423,415\n515,917,-361\n-336,658,858\n95,138,22\n-476,619,847\n-340,-569,-846\n567,-361,727\n-460,603,-452\n669,-402,600\n729,430,532\n-500,-761,534\n-322,571,750\n-466,-666,-811\n-429,-592,574\n-355,545,-477\n703,-491,-529\n-328,-685,520\n413,935,-424\n-391,539,-444\n586,-435,557\n-364,-763,-893\n807,-499,-711\n755,-354,-619\n553,889,-390\n\n--- scanner 2 ---\n649,640,665\n682,-795,504\n-784,533,-524\n-644,584,-595\n-588,-843,648\n-30,6,44\n-674,560,763\n500,723,-460\n609,671,-379\n-555,-800,653\n-675,-892,-343\n697,-426,-610\n578,704,681\n493,664,-388\n-671,-858,530\n-667,343,800\n571,-461,-707\n-138,-166,112\n-889,563,-600\n646,-828,498\n640,759,510\n-630,509,768\n-681,-892,-333\n673,-379,-804\n-742,-814,-386\n577,-820,562\n\n--- scanner 3 ---\n-589,542,597\n605,-692,669\n-500,565,-823\n-660,373,557\n-458,-679,-417\n-488,449,543\n-626,468,-788\n338,-750,-386\n528,-832,-391\n562,-778,733\n-938,-730,414\n543,643,-506\n-524,371,-870\n407,773,750\n-104,29,83\n378,-903,-323\n-778,-728,485\n426,699,580\n-438,-605,-362\n-469,-447,-387\n509,732,623\n647,635,-688\n-868,-804,481\n614,-800,639\n595,780,-596\n\n--- scanner 4 ---\n727,592,562\n-293,-554,779\n441,611,-461\n-714,465,-776\n-743,427,-804\n-660,-479,-426\n832,-632,460\n927,-485,-438\n408,393,-506\n466,436,-512\n110,16,151\n-258,-428,682\n-393,719,612\n-211,-452,876\n808,-476,-593\n-575,615,604\n-485,667,467\n-680,325,-822\n-627,-443,-432\n872,-547,-609\n833,512,582\n807,604,487\n839,-516,451\n891,-625,532\n-652,-548,-490\n30,-46,-14",
    79,
    0L,
    "--- scanner 0 ---\n536,703,543\n-132,-41,-64\n-429,350,-809\n-477,567,455\n-369,-732,-732\n-806,-680,810\n502,729,469\n-417,400,-831\n497,-739,-564\n-545,-726,-743\n271,357,-605\n13,-26,94\n508,-654,-647\n-581,-685,774\n396,-908,355\n-481,568,418\n-634,-665,910\n306,410,-668\n540,739,539\n465,-656,-423\n-474,-628,-795\n-463,568,426\n254,306,-662\n358,-788,353\n-396,313,-691\n251,-873,452\n\n--- scanner 1 ---\n405,377,-919\n-471,604,350\n479,506,644\n495,326,707\n-407,-663,-846\n618,-537,318\n-584,506,-618\n-577,-375,640\n682,-442,283\n-579,-587,598\n612,361,-928\n-253,-680,-729\n512,382,-895\n531,379,751\n-468,-460,659\n-262,-699,-913\n974,-376,-550\n539,-333,326\n917,-437,-630\n-422,787,372\n48,7,-59\n-451,618,404\n-697,390,-626\n-766,485,-587\n971,-556,-663\n\n--- scanner 2 ---\n-652,-598,521\n680,708,-671\n401,-847,-328\n-666,588,522\n-629,-292,-571\n256,-750,-363\n-576,-428,-551\n-698,690,-828\n640,385,384\n698,679,-614\n-801,-392,-556\n490,-475,680\n-594,-543,549\n27,-15,66\n-878,546,524\n-703,476,-864\n741,661,-790\n-793,527,633\n506,-675,668\n-736,691,-860\n-646,-597,737\n650,579,455\n363,-849,-448\n-77,-44,-102\n636,387,485\n609,-565,657\n\n--- scanner 3 ---\n-694,-480,675\n-79,48,-39\n-713,754,292\n329,-686,-566\n352,-717,-653\n689,385,454\n-736,862,-562\n-619,615,350\n687,515,-478\n754,359,540\n-741,-551,707\n-416,-641,-826\n780,-406,714\n757,427,547\n-766,670,-548\n376,-703,-591\n-676,-675,645\n616,-364,628\n-413,-651,-750\n-645,601,324\n-837,864,-558\n618,546,-543\n651,-444,702\n-438,-700,-839\n735,493,-571\n\n--- scanner 4 ---\n-530,746,312\n786,-443,556\n-889,-559,548\n707,-625,-700\n-597,746,-565\n-695,733,324\n797,-456,613\n521,468,-520\n798,-448,530\n-820,-811,-882\n-905,-712,-989\n700,666,439\n759,795,432\n637,796,456\n-877,-533,314\n-887,-526,362\n671,-621,-704\n-575,696,330\n-940,-809,-844\n-478,699,-504\n-654,717,-547\n-56,111,-123\n529,444,-427\n618,407,-518\n697,-695,-738\n\n--- scanner 5 ---\n-622,-664,638\n91,56,-46\n-584,-588,503\n-597,-542,-844\n637,-732,382\n541,-389,-695\n608,849,773\n-824,472,329\n-715,664,-901\n-807,472,398\n696,824,658\n-748,513,423\n734,-704,553\n-603,-571,-996\n702,-641,519\n-564,648,-902\n-546,-560,-802\n529,757,-875\n-774,670,-893\n710,934,780\n-633,-647,616\n45,186,-187\n571,805,-747\n469,-300,-758\n548,722,-801\n470,-365,-800\n\n--- scanner 6 ---\n-63,90,49\n498,853,-791\n578,-569,-624\n-655,-638,533\n625,-555,-405\n612,-558,-546\n481,703,-791\n472,508,699\n-676,-594,581\n641,-783,554\n689,489,688\n-597,648,491\n-588,829,-432\n447,-795,466\n-611,-448,-552\n-628,702,-523\n517,666,-715\n-730,-425,-642\n-606,719,-512\n-714,-437,-442\n-537,611,499\n-462,665,425\n597,478,703\n460,-784,616\n-648,-556,677\n\n--- scanner 7 ---\n845,881,-301\n-501,-349,486\n-315,-608,-344\n-480,685,-388\n718,980,-308\n915,979,627\n-279,-529,-320\n749,-436,411\n-508,-323,638\n-557,-346,709\n744,-481,476\n738,-636,412\n-431,775,-306\n-486,759,-520\n378,-599,-409\n-575,569,537\n538,-579,-380\n94,37,37\n-656,696,463\n931,951,534\n931,865,728\n448,-570,-525\n-739,579,511\n-301,-469,-277\n782,925,-452\n\n--- scanner 8 ---\n-642,587,781\n449,768,-502\n-541,-248,454\n-652,688,-666\n-657,-301,345\n388,565,306\n-711,575,-617\n-597,592,716\n31,60,-133\n-705,-303,534\n-728,553,-628\n518,471,325\n-449,-629,-686\n-462,-608,-643\n399,395,424\n573,-579,-519\n-49,145,4\n485,-560,410\n365,-564,556\n408,793,-443\n-580,-592,-693\n542,-671,-541\n408,862,-426\n380,-463,470\n-442,598,780\n580,-773,-513\n\n--- scanner 9 ---\n511,-520,557\n831,490,487\n-549,-659,-680\n536,-615,-490\n165,-84,33\n652,-552,474\n-516,602,-544\n-439,-556,-691\n-267,-451,305\n-822,579,548\n-571,544,-572\n-16,-6,-106\n841,452,-616\n-517,596,-760\n-364,-306,340\n584,-594,-534\n820,314,481\n680,-622,-572\n883,419,585\n-722,751,533\n-639,-540,-703\n735,-563,559\n947,393,-650\n-686,526,528\n905,520,-588\n-265,-413,425\n\n--- scanner 10 ---\n-737,650,-388\n799,760,694\n-389,-453,768\n-667,-803,-543\n537,-419,977\n573,-564,910\n699,461,-254\n-644,698,-366\n-657,-874,-737\n556,-410,793\n-672,581,-419\n-653,-773,-735\n680,814,727\n78,-167,23\n-376,-475,827\n861,-734,-576\n649,-736,-534\n776,-625,-595\n-649,292,813\n860,817,614\n-624,339,830\n50,-20,162\n-465,-450,778\n-562,315,844\n903,382,-262\n794,525,-219\n\n--- scanner 11 ---\n99,76,34\n-676,680,608\n-416,-818,449\n-383,-733,-582\n-278,-673,-485\n-263,-587,-586\n-297,-719,373\n610,574,866\n544,-363,576\n743,689,-672\n-817,391,-552\n-607,706,535\n601,429,776\n-805,494,-602\n-779,332,-498\n699,-427,570\n703,798,-570\n759,-798,-323\n643,-689,-270\n-605,648,465\n648,653,-655\n-286,-837,543\n-21,-98,124\n581,458,936\n807,-609,-325\n744,-362,565\n\n--- scanner 12 ---\n-425,-551,749\n399,-500,-745\n621,-564,617\n458,551,789\n-771,404,712\n-635,-782,-410\n141,-60,-11\n-407,-500,691\n427,311,-461\n434,438,-362\n-786,391,773\n-533,-750,-406\n578,374,-417\n420,485,830\n376,378,782\n-602,225,-477\n535,-509,559\n-733,594,734\n652,-499,714\n-466,-660,638\n375,-461,-524\n-501,241,-363\n13,-15,-141\n392,-416,-592\n-585,-935,-464\n-408,225,-391\n\n--- scanner 13 ---\n-869,-562,284\n-626,-660,-761\n863,541,-592\n-615,-577,-676\n594,-454,-831\n-713,476,667\n915,554,-452\n621,-462,713\n528,750,450\n680,-516,627\n-640,407,641\n-783,478,609\n866,578,-609\n-705,797,-876\n-875,786,-758\n662,-561,828\n-658,-801,-678\n524,593,486\n-852,-619,335\n-799,667,-857\n-817,-556,388\n479,-362,-887\n490,705,572\n101,-76,-131\n-40,-1,16\n414,-474,-816\n\n--- scanner 14 ---\n441,817,-678\n-598,650,753\n371,-553,781\n-510,-749,-608\n9,-9,185\n364,-412,661\n-698,-767,-537\n-695,787,-546\n-93,144,106\n676,-367,-546\n-564,746,-667\n478,752,-535\n-588,-350,394\n565,773,-725\n-587,-306,537\n-678,698,-692\n-773,-762,-605\n-393,632,689\n597,749,893\n827,-282,-517\n-490,578,681\n380,-405,668\n808,-318,-662\n578,692,850\n579,917,825\n-578,-442,497\n\n--- scanner 15 ---\n711,434,636\n-814,919,616\n-367,796,-569\n667,-597,612\n637,-360,-736\n668,-381,618\n699,-371,-662\n-351,789,-562\n591,759,-398\n786,439,704\n128,62,-70\n-793,-527,-586\n722,-569,670\n-754,-553,694\n600,895,-438\n601,851,-295\n-796,-491,821\n-663,834,645\n-795,883,761\n-690,-470,-654\n-728,-676,-612\n-690,-525,762\n639,-287,-734\n-358,648,-682\n523,452,707\n\n--- scanner 16 ---\n509,-793,667\n878,553,565\n-582,760,-790\n-599,-400,-777\n581,-549,-837\n-498,-416,-891\n-405,-400,-863\n-530,575,-848\n769,655,525\n-8,-11,-79\n538,-953,687\n90,-139,81\n-529,308,461\n402,325,-509\n-282,-582,524\n-347,-587,569\n654,-883,658\n-718,306,509\n-609,253,625\n-283,-593,724\n-632,607,-844\n430,-486,-793\n395,241,-662\n437,-613,-892\n733,638,620\n378,286,-730\n\n--- scanner 17 ---\n754,602,-677\n-722,-580,-369\n-439,722,669\n-372,731,-647\n16,-1,-34\n164,118,125\n433,-796,-390\n474,-459,828\n943,619,739\n-765,-605,576\n-402,923,-633\n697,631,-695\n873,649,-738\n-700,-639,-356\n592,-518,899\n-756,-789,557\n566,-719,-309\n475,-540,761\n-725,-590,-445\n692,-807,-381\n-611,-722,593\n-550,783,796\n911,620,701\n-441,764,881\n-301,925,-637\n947,834,706\n\n--- scanner 18 ---\n113,-179,93\n-709,565,-575\n-585,-728,588\n812,527,-496\n-496,315,704\n-367,364,615\n939,-532,762\n727,472,-351\n701,704,557\n-312,-886,-799\n944,-633,873\n-779,594,-691\n-533,-891,548\n499,-602,-827\n-608,-769,451\n-375,-930,-648\n-619,574,-720\n829,432,-460\n460,-823,-851\n757,693,547\n415,-755,-834\n950,-664,733\n-439,-985,-792\n702,691,743\n-341,400,702\n\n--- scanner 19 ---\n765,-723,-476\n-795,553,792\n640,682,-495\n138,123,77\n648,-491,767\n617,736,-578\n-556,-542,581\n-679,-488,-787\n-738,-463,-828\n-755,-394,-828\n508,578,559\n779,-845,-366\n-638,-549,725\n489,795,570\n611,-431,759\n665,-356,833\n478,703,555\n80,10,-59\n-594,-570,677\n-783,747,704\n-705,828,-630\n677,-838,-451\n-784,566,642\n-495,837,-652\n598,810,-543\n-646,836,-636\n\n--- scanner 20 ---\n698,-671,896\n-629,-578,570\n882,-690,887\n633,-486,-344\n815,-654,742\n862,582,-466\n-307,396,-533\n-671,-366,572\n556,494,383\n-327,471,-605\n-403,796,727\n-526,-508,-812\n-690,-451,681\n-48,50,4\n595,399,452\n-741,-541,-816\n-435,627,698\n-381,613,678\n611,-348,-461\n-527,-546,-780\n587,515,565\n887,614,-414\n-361,524,-418\n552,-419,-343\n867,468,-504\n\n--- scanner 21 ---\n591,695,-643\n-461,-434,378\n876,-525,845\n447,688,-725\n-609,715,615\n858,-320,-565\n-724,-437,365\n752,-379,-599\n725,-496,760\n816,-447,-627\n413,806,524\n-598,737,632\n-528,629,-524\n-660,-471,-643\n-649,-505,360\n405,746,561\n-606,-523,-455\n-605,-529,-671\n-638,732,-535\n339,881,578\n-110,154,-71\n857,-380,809\n38,67,-3\n-518,755,742\n495,667,-681\n-728,613,-512\n\n--- scanner 22 ---\n417,869,-670\n-390,-523,-643\n668,-601,466\n-449,460,555\n-411,652,-704\n757,407,838\n-332,-429,-504\n-399,817,-717\n-14,26,-96\n-635,458,588\n549,-578,556\n-401,470,562\n-674,-468,562\n-687,-471,703\n-364,-497,-657\n460,693,-688\n508,-376,-588\n902,442,741\n478,750,-717\n518,-305,-394\n652,-589,666\n109,117,38\n-417,772,-667\n795,527,840\n-640,-457,484\n522,-406,-396\n\n--- scanner 23 ---\n-800,-816,-415\n486,376,-362\n-411,496,636\n260,419,326\n-938,-671,519\n1,-24,20\n359,-713,278\n-930,-835,-509\n-611,650,-431\n668,-637,-556\n429,325,-345\n-833,-731,616\n423,429,-397\n282,369,338\n-953,-585,620\n400,332,305\n296,-609,383\n-437,334,532\n-919,-717,-339\n348,-707,454\n527,-720,-588\n444,-635,-599\n-540,682,-574\n-186,11,-59\n-399,393,586\n-609,708,-501\n\n--- scanner 24 ---\n661,560,-615\n-838,-878,-425\n756,-980,519\n-430,-499,528\n-840,792,-299\n-891,-834,-389\n730,337,599\n-711,491,690\n-613,-477,577\n-835,768,-406\n-827,499,803\n792,592,-663\n-711,-771,-373\n656,286,631\n-856,801,-506\n-42,-81,88\n841,216,626\n-599,-531,557\n767,-854,387\n754,-461,-726\n-732,460,872\n859,-869,436\n668,-426,-620\n626,-548,-657\n802,511,-703\n\n--- scanner 25 ---\n871,758,-518\n-441,-709,335\n458,576,380\n704,-742,-632\n-721,571,786\n-803,-452,-835\n-752,658,763\n-711,565,-686\n828,638,-529\n-687,377,-660\n392,-542,358\n-686,530,-584\n818,761,-520\n-888,-342,-883\n480,483,428\n-798,-414,-880\n324,-535,361\n-399,-623,499\n-54,-43,48\n511,548,497\n350,-449,426\n807,-776,-756\n-738,596,739\n81,137,-43\n716,-700,-860\n-479,-697,464\n\n--- scanner 26 ---\n-901,-451,391\n-534,-435,-744\n-383,810,622\n-7,71,53\n474,684,458\n-861,602,-891\n-769,-567,440\n-571,-578,-679\n-457,735,676\n605,-576,840\n-920,-596,355\n417,-505,822\n680,-508,835\n384,612,-707\n471,-446,-673\n462,781,-698\n565,644,585\n-864,517,-869\n554,666,568\n-909,737,-873\n-658,-388,-700\n-449,675,639\n585,-421,-625\n602,-567,-714\n397,746,-782\n\n--- scanner 27 ---\n-490,-562,-654\n501,516,490\n-310,-612,762\n560,738,-569\n587,-506,-667\n-782,970,-551\n-729,935,-614\n-289,-544,-609\n424,433,614\n606,-412,-491\n-833,987,-608\n378,-468,689\n-404,-503,-708\n391,546,639\n487,738,-654\n-436,705,597\n451,-491,621\n-400,701,593\n-321,-555,775\n567,-397,668\n81,40,90\n582,667,-630\n-410,739,523\n-309,-529,770\n657,-413,-678\n\n--- scanner 28 ---\n69,84,-59\n-289,-502,-551\n595,-623,583\n-456,920,-639\n583,-423,567\n778,439,713\n-576,755,732\n714,-491,-517\n-307,-690,-480\n-693,808,704\n879,437,717\n739,598,-801\n-377,912,-843\n-645,786,797\n741,640,-589\n-544,-542,669\n742,-546,-575\n-520,955,-734\n606,-513,686\n526,-545,-569\n-671,-633,709\n-561,-553,691\n-294,-427,-483\n808,631,727\n630,579,-716\n\n--- scanner 29 ---\n554,-452,430\n712,317,-664\n554,-470,422\n-577,514,-619\n-735,-733,754\n-625,-635,-349\n419,547,547\n-441,819,564\n-504,514,-704\n-518,736,440\n809,-385,-687\n30,-54,-66\n722,-406,-827\n-599,585,-680\n772,-418,-805\n-123,-145,56\n765,282,-601\n-662,-695,652\n-660,-788,804\n383,551,543\n-650,-675,-301\n-723,-747,-314\n-408,706,589\n555,-466,459\n393,513,473\n-30,28,95\n816,265,-658\n\n--- scanner 30 ---\n648,439,-720\n-883,-704,-653\n760,676,623\n681,-717,482\n288,-461,-535\n739,-762,425\n752,755,827\n-682,-622,380\n-665,-711,526\n278,-470,-658\n-587,838,467\n-793,461,-725\n588,-655,448\n-850,825,464\n-702,588,-685\n-662,843,535\n-870,-779,-787\n795,612,718\n-796,-670,405\n595,556,-748\n5,-51,26\n235,-459,-538\n-131,33,-79\n-805,-695,-822\n-713,571,-707\n626,487,-813"
  )

  test("readOneScannerInput") {
    assert(_19.readOneScannerInput("--- scanner 101 ---\n536,705,543\n-132,-41,-64\n-429,350,-809")
      .equals((101, Set(
        (536, 705, 543),
        (-132, -41, -64),
        (-429, 350, -809)))))
  }

  test("affix three identical") {
    assert(_19.affix(Set((1, 0, 0), (3, 0, 0), (1, 1, 0)),
      Set((1, 0, 0), (3, 0, 0), (1, 1, 0)))
      .head
      .equals(AffixResult((0, 0, 0), Set((1, 0, 0), (3, 0, 0), (1, 1, 0)), Set.empty)))
  }

  test("affix three translated") {
    assert(_19.affix(Set((0, 0, 0), (2, 0, 0), (0, 1, 0)),
      Set((1, 0, 0), (3, 0, 0), (1, 1, 0)))
      .head
      .equals(AffixResult((-1, 0, 0), Set((0, 0, 0), (2, 0, 0), (0, 1, 0)), Set.empty)))
    assert(_19.affix(Set((0, 0, 0), (2, 0, 0), (0, 1, 0)),
      Set((1, 1, 0), (1, 0, 0), (3, 0, 0)))
      .head
      .equals(AffixResult((-1, 0, 0), Set((0, 0, 0), (2, 0, 0), (0, 1, 0)), Set.empty)))
  }

  test("affix three unaffected by extra points") {
    assert(_19.affix(Set((0, 0, 0), (2, 0, 0), (0, 1, 0)),
      Set((1, -1, 0), (3, -1, 0), (1, 0, 0), (20, 1, 0)))
      .head
      .equals(AffixResult((-1, 1, 0), Set((0, 0, 0), (2, 0, 0), (0, 1, 0)), Set((19, 2, 0)))))
  }

  test("affix three rotated") {
    val actual = _19.turnAndAffix(Set((0, 1, 0), (0, -1, 0), (1, 1, 0)),
      Set((1, 0, 0), (3, 0, 0), (1, 1, 0)))
    assert(actual.head
      .equals(TurnAndAffixResult(19, (0,2,0), Set((0, 1, 0), (0, -1, 0), (1, 1, 0)), Set.empty, 3)))
  }

  test("is in box") {
    assert(_19.isInBox((-500,1000,-1500), (500,0,-500)))
    assert(_19.isInBox((-500,-1500,1000), (500,-500,0)))
  }

  test("is not in box") {
    assert(!_19.isInBox((1501,0,-500), (500,0,-500)))
    assert(!_19.isInBox((-500,1501,0), (-500,500,0)))
    assert(!_19.isInBox((-500,5,1501), (-500,5,500)))
  }
}

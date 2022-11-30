package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _22Test extends AdventOfCodeTestBase {

  test("Part 1 reduced sample 1") {
    assert(_22._1("on x=10..12,y=10..12,z=10..12\non x=11..13,y=11..13,z=11..13\noff x=9..11,y=9..11,z=9..11\non x=10..10,y=10..10,z=10..10")
      == 39)
  }

  test("Part 1 reduced sample 2") {
    assert(_22._1("on x=-20..26,y=-36..17,z=-47..7\non x=-20..33,y=-21..23,z=-26..28\non x=-22..28,y=-29..23,z=-38..16\non x=-46..7,y=-6..46,z=-50..-1\non x=-49..1,y=-3..46,z=-24..28\non x=2..47,y=-22..22,z=-23..27\non x=-27..23,y=-28..26,z=-21..29\non x=-39..5,y=-6..47,z=-3..44\non x=-30..21,y=-8..43,z=-13..34\non x=-22..26,y=-27..20,z=-29..19\noff x=-48..-32,y=26..41,z=-47..-37\non x=-12..35,y=6..50,z=-50..-2\noff x=-48..-32,y=-32..-16,z=-15..-5\non x=-18..26,y=-33..15,z=-7..46\noff x=-40..-22,y=-38..-28,z=23..41\non x=-16..35,y=-41..10,z=-47..6\noff x=-32..-23,y=11..30,z=-14..3\non x=-49..-5,y=-3..45,z=-29..18\noff x=18..30,y=-20..-8,z=-3..13\non x=-41..9,y=-7..43,z=-33..15\non x=-54112..-39298,y=-85059..-49293,z=-27449..7877\non x=967..23432,y=45373..81175,z=27513..53682")
      == 590784)
  }

  testAndRun(22, _22._1, _22._2,
    "on x=-5..47,y=-31..22,z=-19..33\non x=-44..5,y=-27..21,z=-14..35\non x=-49..-1,y=-11..42,z=-10..38\non x=-20..34,y=-40..6,z=-44..1\noff x=26..39,y=40..50,z=-2..11\non x=-41..5,y=-41..6,z=-36..8\noff x=-43..-33,y=-45..-28,z=7..25\non x=-33..15,y=-32..19,z=-34..11\noff x=35..47,y=-46..-34,z=-11..5\non x=-14..36,y=-6..44,z=-16..29\non x=-57795..-6158,y=29564..72030,z=20435..90618\non x=36731..105352,y=-21140..28532,z=16094..90401\non x=30999..107136,y=-53464..15513,z=8553..71215\non x=13528..83982,y=-99403..-27377,z=-24141..23996\non x=-72682..-12347,y=18159..111354,z=7391..80950\non x=-1060..80757,y=-65301..-20884,z=-103788..-16709\non x=-83015..-9461,y=-72160..-8347,z=-81239..-26856\non x=-52752..22273,y=-49450..9096,z=54442..119054\non x=-29982..40483,y=-108474..-28371,z=-24328..38471\non x=-4958..62750,y=40422..118853,z=-7672..65583\non x=55694..108686,y=-43367..46958,z=-26781..48729\non x=-98497..-18186,y=-63569..3412,z=1232..88485\non x=-726..56291,y=-62629..13224,z=18033..85226\non x=-110886..-34664,y=-81338..-8658,z=8914..63723\non x=-55829..24974,y=-16897..54165,z=-121762..-28058\non x=-65152..-11147,y=22489..91432,z=-58782..1780\non x=-120100..-32970,y=-46592..27473,z=-11695..61039\non x=-18631..37533,y=-124565..-50804,z=-35667..28308\non x=-57817..18248,y=49321..117703,z=5745..55881\non x=14781..98692,y=-1341..70827,z=15753..70151\non x=-34419..55919,y=-19626..40991,z=39015..114138\non x=-60785..11593,y=-56135..2999,z=-95368..-26915\non x=-32178..58085,y=17647..101866,z=-91405..-8878\non x=-53655..12091,y=50097..105568,z=-75335..-4862\non x=-111166..-40997,y=-71714..2688,z=5609..50954\non x=-16602..70118,y=-98693..-44401,z=5197..76897\non x=16383..101554,y=4615..83635,z=-44907..18747\noff x=-95822..-15171,y=-19987..48940,z=10804..104439\non x=-89813..-14614,y=16069..88491,z=-3297..45228\non x=41075..99376,y=-20427..49978,z=-52012..13762\non x=-21330..50085,y=-17944..62733,z=-112280..-30197\non x=-16478..35915,y=36008..118594,z=-7885..47086\noff x=-98156..-27851,y=-49952..43171,z=-99005..-8456\noff x=2032..69770,y=-71013..4824,z=7471..94418\non x=43670..120875,y=-42068..12382,z=-24787..38892\noff x=37514..111226,y=-45862..25743,z=-16714..54663\noff x=25699..97951,y=-30668..59918,z=-15349..69697\noff x=-44271..17935,y=-9516..60759,z=49131..112598\non x=-61695..-5813,y=40978..94975,z=8655..80240\noff x=-101086..-9439,y=-7088..67543,z=33935..83858\noff x=18020..114017,y=-48931..32606,z=21474..89843\noff x=-77139..10506,y=-89994..-18797,z=-80..59318\noff x=8476..79288,y=-75520..11602,z=-96624..-24783\non x=-47488..-1262,y=24338..100707,z=16292..72967\noff x=-84341..13987,y=2429..92914,z=-90671..-1318\noff x=-37810..49457,y=-71013..-7894,z=-105357..-13188\noff x=-27365..46395,y=31009..98017,z=15428..76570\noff x=-70369..-16548,y=22648..78696,z=-1892..86821\non x=-53470..21291,y=-120233..-33476,z=-44150..38147\noff x=-93533..-4276,y=-16170..68771,z=-104985..-24507",
    474140L,
    2758514936282235L,
    "on x=-44..7,y=-48..-4,z=-28..22\non x=-14..37,y=-31..23,z=-46..0\non x=-2..42,y=-40..5,z=-15..34\non x=-6..40,y=-46..8,z=-4..43\non x=-14..40,y=-22..28,z=-39..15\non x=-26..18,y=-36..18,z=-22..24\non x=-1..46,y=-14..39,z=-6..47\non x=-9..43,y=-19..35,z=3..49\non x=-2..49,y=-19..35,z=-44..9\non x=-14..31,y=-31..23,z=-15..37\noff x=-15..0,y=10..20,z=-1..11\non x=-13..39,y=-7..40,z=-16..29\noff x=-36..-23,y=-22..-7,z=22..32\non x=-42..8,y=-27..23,z=-24..22\noff x=30..44,y=-6..11,z=-25..-12\non x=-14..37,y=-20..31,z=-27..19\noff x=-11..7,y=24..39,z=-49..-36\non x=-40..7,y=-46..2,z=-8..36\noff x=-28..-9,y=9..19,z=-3..13\non x=-32..19,y=-46..4,z=-10..35\non x=-57669..-47415,y=-34767..-16398,z=30569..62270\non x=-26838..-18058,y=-44045..-32246,z=-69820..-54662\non x=23944..45874,y=-56447..-36843,z=51476..78050\non x=-60295..-37768,y=15477..31617,z=-62436..-53796\non x=7279..37283,y=-14827..827,z=-81548..-71878\non x=14873..45919,y=-55604..-29161,z=-73563..-62510\non x=8304..25869,y=60634..75835,z=-38723..-10959\non x=10010..30443,y=-27861..-18913,z=75221..96082\non x=-19174..1724,y=-1716..15662,z=-83027..-75955\non x=2933..9957,y=51319..54072,z=43068..71431\non x=-54485..-38845,y=-59089..-33388,z=39472..59916\non x=44991..60045,y=39800..52961,z=-50242..-11489\non x=29344..48984,y=-12037..3869,z=-80327..-68023\non x=-32654..1023,y=30977..64895,z=52313..68196\non x=-61627..-36736,y=-16352..-227,z=59819..74021\non x=-70800..-32442,y=-60912..-27524,z=-53014..-34685\non x=37172..54896,y=-33170..-23103,z=39779..62817\non x=33016..45483,y=-29966..-21595,z=61920..75220\non x=40062..58134,y=-66288..-45661,z=-13163..4407\non x=48899..63190,y=-40057..-19858,z=-57642..-39508\non x=-13394..5479,y=-33828..-8938,z=-81107..-59473\non x=-53077..-46727,y=26122..44435,z=41182..56258\non x=-882..34139,y=72487..90656,z=1449..34384\non x=-55069..-25492,y=57603..60372,z=27312..37818\non x=11637..35186,y=-2112..35130,z=-82659..-58284\non x=-1963..19390,y=31372..43852,z=55664..78279\non x=-37450..-13706,y=56862..79450,z=42506..58352\non x=-11194..7926,y=-54868..-41855,z=59371..69838\non x=-68076..-47918,y=-7134..19211,z=54036..59607\non x=56398..65176,y=9373..35857,z=-59779..-33781\non x=-29722..-13167,y=52154..61111,z=34337..71865\non x=-41452..-21137,y=38656..58801,z=-74267..-49536\non x=29554..32383,y=-16535..13186,z=68589..83493\non x=11565..19985,y=-66635..-51018,z=40523..55317\non x=-16115..8224,y=-93747..-59306,z=-30707..-16928\non x=41464..62560,y=28328..43290,z=49899..69729\non x=-78292..-62498,y=-26428..-963,z=33334..43746\non x=-62268..-27494,y=52017..78451,z=-27229..-9290\non x=74538..81687,y=-6868..9462,z=-42485..-7405\non x=-70139..-51493,y=33391..58326,z=-47685..-18900\non x=-15600..-10169,y=-76996..-58016,z=26710..50743\non x=-38135..-33369,y=-68467..-37987,z=-68299..-34906\non x=-32926..-12278,y=-17049..5935,z=71187..95480\non x=-68775..-38703,y=31347..51664,z=-66756..-28690\non x=-17763..-444,y=-25060..-9321,z=-82848..-59956\non x=9259..34670,y=-63918..-46717,z=-62805..-36148\non x=-38860..-17888,y=-83191..-60240,z=15932..31388\non x=-27738..-12501,y=-58688..-48917,z=50240..63344\non x=-9552..12298,y=-82967..-63566,z=-31665..-8131\non x=-3262..23176,y=-8404..-682,z=68987..87074\non x=-492..24054,y=76438..84125,z=-3124..15757\non x=-6636..23517,y=24061..51082,z=58265..80778\non x=-11300..13852,y=36935..72289,z=54439..75741\non x=-36722..-5253,y=38765..48140,z=60050..80209\non x=-68641..-42826,y=10138..20406,z=-48840..-38994\non x=37946..71780,y=-11716..-3224,z=-76794..-53565\non x=14937..42416,y=-74948..-54941,z=17561..31037\non x=-61298..-48316,y=21641..32156,z=38100..61810\non x=33337..44565,y=-61197..-41116,z=-63088..-42095\non x=55485..80696,y=-12185..7524,z=38733..61721\non x=19994..48088,y=-63968..-29433,z=34529..51173\non x=57918..85515,y=-896..14685,z=-34920..-21663\non x=-28290..-18544,y=-74670..-62248,z=-33511..-13509\non x=-91602..-59099,y=-28478..5896,z=-22843..268\non x=47808..64741,y=-62234..-56202,z=-34499..-15875\non x=-5439..29218,y=40801..61745,z=-73755..-42460\non x=66499..87188,y=-47632..-14352,z=-2835..27458\non x=6077..20827,y=59218..81594,z=22627..42843\non x=-76720..-44054,y=-58925..-27476,z=13431..37577\non x=-67945..-52903,y=-59540..-48436,z=-3959..28890\non x=63669..81588,y=3522..24426,z=-13019..4078\non x=66389..80203,y=7859..36184,z=-21763..5299\non x=8985..37402,y=67471..77806,z=23845..39004\non x=-79912..-67840,y=11112..26176,z=13492..41306\non x=9298..39655,y=49448..70324,z=-53552..-37003\non x=5868..25390,y=60778..82461,z=-3147..29756\non x=62208..85012,y=24013..43359,z=-4030..20786\non x=25563..60394,y=-77470..-53600,z=-24444..-10882\non x=-85136..-64905,y=9280..27843,z=-24178..-13919\non x=-46556..-20968,y=61562..77589,z=-55628..-32861\non x=56560..95299,y=-33827..-18248,z=-5634..1038\non x=-49836..-26941,y=-72556..-64453,z=-21395..-5169\non x=-15142..1630,y=29424..55194,z=-80676..-57685\non x=40758..58341,y=-37580..-17953,z=47185..63053\non x=-83532..-54519,y=14164..37120,z=-8060..28402\non x=12818..44502,y=55987..71453,z=-41223..-19473\non x=-4840..2988,y=-65963..-42391,z=43719..69256\non x=-17605..-5232,y=44129..60456,z=-67226..-43598\non x=47446..74934,y=-54664..-29117,z=12932..43430\non x=55486..76987,y=-56915..-46806,z=20053..35664\non x=-32418..-5191,y=-89850..-69798,z=14068..34962\non x=-67032..-37418,y=34844..51405,z=33127..59954\non x=-13655..14693,y=-75994..-50458,z=-72104..-36662\non x=66339..92744,y=-18657..-7726,z=21490..33317\non x=53091..73056,y=36859..60782,z=14612..29840\non x=-44743..-29546,y=41851..61121,z=22465..54041\non x=-9987..1366,y=11297..38316,z=68452..83250\non x=-80587..-54313,y=18931..30390,z=-48007..-36485\non x=-36867..-14328,y=40625..54265,z=-74356..-41512\non x=15699..30877,y=-70192..-46143,z=37310..62324\non x=-71614..-38143,y=-41704..-16440,z=-66155..-38475\non x=-62484..-42887,y=-4231..15340,z=-75961..-45592\non x=46006..67614,y=26585..47989,z=6308..42157\non x=45024..68068,y=-33616..180,z=30942..60664\non x=14301..42992,y=44850..66137,z=-62651..-38951\non x=-5142..13215,y=-47989..-29881,z=-84658..-68851\non x=-15828..12464,y=-85703..-72043,z=-26704..414\non x=-85704..-48060,y=-49145..-21157,z=-29687..-11621\non x=-6374..26062,y=-43479..-21489,z=-75304..-65078\non x=-65088..-40345,y=-70338..-44899,z=-30731..-18492\non x=-33236..-3437,y=-60726..-34937,z=-74647..-56493\non x=-89271..-55975,y=29143..35214,z=-12958..11803\non x=7570..22211,y=-36457..-30528,z=-87550..-52770\non x=-21844..7612,y=-13339..3854,z=63591..80571\non x=62681..80087,y=-15165..-1484,z=-16312..4733\non x=30764..51898,y=-54770..-33399,z=-69113..-49263\non x=-14706..3333,y=-34078..-20149,z=57303..79672\non x=-57882..-46150,y=-2602..5065,z=36801..61301\non x=-6409..4744,y=-23298..-4086,z=77909..82727\non x=28231..50858,y=-25112..1285,z=-82337..-62613\non x=-60169..-35664,y=45331..71566,z=-52986..-27989\non x=16548..37232,y=29641..41926,z=44045..64175\non x=-27530..-5944,y=-54250..-41450,z=48642..63840\non x=-33752..-18800,y=45216..52417,z=-80150..-42007\non x=-46681..-39468,y=27115..51324,z=41263..68851\non x=-77564..-58032,y=1512..31954,z=-29462..-6724\non x=25989..54117,y=-22057..7911,z=54881..74834\non x=69655..77091,y=-36962..-8338,z=-29623..-13034\non x=36547..45906,y=-19538..-1870,z=54069..80852\non x=48119..58288,y=-6195..22597,z=53580..70770\non x=-79230..-45439,y=35671..60446,z=-1874..15269\non x=-8784..1459,y=-31199..64,z=70551..83107\non x=1880..12553,y=34951..52913,z=66407..90779\non x=-65227..-45109,y=-986..23543,z=-56927..-41562\non x=38071..55190,y=-1152..16116,z=52049..80225\non x=-48564..-33827,y=-53997..-37034,z=50494..59423\non x=-3880..15646,y=-95096..-58701,z=487..15462\non x=32448..44054,y=-61760..-31951,z=50025..56984\non x=49465..69635,y=-39603..-22099,z=-52151..-36714\non x=-75231..-49932,y=-60778..-54855,z=-26493..-5370\non x=35578..48084,y=-69596..-41217,z=-55956..-28280\non x=-60448..-49199,y=52341..68896,z=-6151..12805\non x=-72886..-58561,y=-20435..4228,z=25829..48873\non x=-15783..-12488,y=-51668..-27553,z=-86556..-62238\non x=-62994..-42998,y=-80730..-58580,z=-3922..18675\non x=-89876..-67328,y=-33822..-12798,z=-22322..2742\non x=-43015..-21469,y=46582..68250,z=-37517..-14243\non x=-32767..-11037,y=49857..71371,z=15732..34955\non x=2240..21661,y=75578..89677,z=3412..23943\non x=31255..50946,y=-41168..-11233,z=-77667..-54127\non x=32599..65952,y=-35521..-10537,z=-56429..-50415\non x=-60138..-26615,y=-54582..-34921,z=31673..49153\non x=-49673..-35379,y=-69100..-60235,z=-2394..5510\non x=-36015..-13347,y=-12119..14151,z=-91559..-66887\non x=-6323..19724,y=-72320..-61811,z=33324..65911\non x=17080..26758,y=-67136..-36305,z=35911..62171\non x=5223..25662,y=-87359..-62299,z=23568..38676\non x=-74373..-59477,y=-4295..11346,z=36901..64445\non x=-34299..-1928,y=11411..25476,z=-91142..-75962\non x=-16791..12614,y=-67861..-40449,z=-62621..-50416\non x=40128..59229,y=48857..75692,z=-51859..-21998\non x=-57970..-36216,y=-4627..8697,z=57979..63945\non x=24584..35472,y=-54536..-26170,z=51165..69055\non x=-8247..15433,y=-36101..-14888,z=73892..77781\non x=-64933..-48690,y=52625..72716,z=19985..44210\non x=-63835..-52109,y=-17596..-5749,z=-61623..-38639\non x=-3838..6784,y=-72953..-58060,z=41661..66294\non x=50897..78898,y=-25992..-12109,z=-60442..-46049\non x=65788..74812,y=-21111..-6276,z=10823..46399\non x=-27607..-9446,y=34460..63127,z=56809..73065\non x=570..34475,y=61988..72741,z=-45705..-35924\non x=-17462..-8489,y=-8626..8820,z=-84202..-61394\non x=-33944..-15274,y=-89456..-56691,z=-33904..-10998\non x=66413..90344,y=-521..34369,z=-41730..-11324\non x=-49505..-14224,y=48346..60470,z=-65804..-32918\non x=-86096..-65120,y=17671..43220,z=-3906..30656\non x=33742..44358,y=-19983..821,z=58363..74256\non x=68866..75878,y=-34904..-11128,z=-44097..-23351\non x=2783..19415,y=58410..90714,z=-28778..-9588\non x=-75845..-59575,y=-46328..-31294,z=-26730..-5162\non x=28782..29236,y=-70340..-61456,z=29839..49236\non x=43385..51055,y=31802..51803,z=32252..56629\non x=-14424..5130,y=-69167..-53231,z=-59193..-37237\non x=4410..29399,y=-86425..-68804,z=21060..41858\non x=-76543..-49298,y=-1254..13198,z=-62944..-31320\non x=58155..76408,y=3385..33072,z=27931..49984\non x=53285..68227,y=9528..16970,z=38731..70004\non x=-93183..-64817,y=25925..48836,z=-21559..11511\non x=41939..75966,y=34833..66872,z=5453..22174\non x=-27905..-13907,y=66380..77173,z=-30940..-2812\non x=-44965..-36722,y=44845..61282,z=-56311..-30434\non x=-18507..4231,y=51538..82637,z=27243..47809\non x=-22666..-8957,y=-70300..-61956,z=-54272..-23440\non x=-71598..-46251,y=40153..63242,z=23221..53691\non x=38505..60525,y=-58882..-40951,z=14362..23621\non x=-40771..-25170,y=60466..77076,z=-26181..-3203\non x=48443..70795,y=38515..60309,z=-40023..-35935\non x=-64973..-51997,y=4324..24699,z=-66495..-47350\non x=48357..63912,y=24009..51643,z=13119..43197\non x=-59219..-45782,y=32426..53977,z=-56899..-22839\noff x=-82601..-62583,y=-29547..-19736,z=10012..44278\noff x=21881..52715,y=-75462..-59619,z=-22352..2451\noff x=-45464..-31923,y=-84778..-57121,z=-36904..-20487\non x=-88151..-75143,y=-8134..10815,z=11680..22378\non x=-39971..-22009,y=-72573..-47306,z=-56516..-30255\noff x=9322..29654,y=-4082..10417,z=-85840..-68364\noff x=-72958..-39349,y=-62169..-48603,z=-38569..-2407\non x=6195..28613,y=60609..78035,z=-51100..-35991\non x=-18884..-2782,y=-56445..-48463,z=54414..73962\noff x=-34686..-14850,y=31925..70449,z=-77698..-48963\non x=-50659..-28136,y=63604..81082,z=2579..9313\non x=5549..35226,y=69786..72274,z=-37289..-21739\noff x=-29792..-6796,y=-81569..-63298,z=19068..40723\noff x=44836..79546,y=-22779..3568,z=-66800..-40145\noff x=-49270..-30969,y=-3396..25485,z=-78171..-59340\noff x=35399..60394,y=-68920..-56505,z=-1293..13695\non x=-7191..-2494,y=-83630..-63883,z=33504..51910\non x=-18980..-14896,y=65339..79062,z=-18683..1270\noff x=43093..61831,y=14636..42577,z=-74949..-44384\non x=55863..72523,y=-11248..15275,z=40692..55672\noff x=49627..71091,y=-31378..-15169,z=40187..49905\non x=-71583..-61447,y=20612..27406,z=-47136..-22936\noff x=-22955..-508,y=-73996..-60313,z=16824..51592\noff x=-31772..-17942,y=3692..29348,z=-81806..-61057\noff x=-36116..-14323,y=-47112..-21136,z=71357..88454\noff x=39462..69248,y=-67362..-37455,z=-46036..-22428\non x=-78767..-56235,y=-31380..-11432,z=19828..51353\non x=-78645..-65106,y=10312..37198,z=2401..24591\non x=6402..40318,y=-16548..2555,z=73595..84063\non x=-55615..-27722,y=49943..68701,z=-28634..-4623\noff x=42562..67015,y=35610..46866,z=34070..42249\non x=8843..44449,y=64895..88067,z=18310..35158\non x=52861..75312,y=-17051..2907,z=-53407..-21823\non x=-78385..-62728,y=-21841..5253,z=-47028..-22568\non x=-10495..14623,y=-80377..-59812,z=-34116..-22218\noff x=-21996..-2742,y=37228..50250,z=-71033..-60924\non x=56697..77982,y=-7450..3730,z=9771..41652\noff x=-67274..-50820,y=-5734..5640,z=-56831..-37003\non x=11030..31524,y=37918..52771,z=-71435..-60852\non x=-83320..-65072,y=-32355..-11917,z=-26967..-18032\non x=-43677..-35148,y=-88398..-69549,z=-23854..1908\non x=-49572..-38348,y=-46258..-17216,z=48043..66417\non x=37384..75100,y=-52354..-42729,z=-43047..-30541\noff x=-53861..-35852,y=-5143..18339,z=-83681..-51951\non x=-78236..-64745,y=2378..20167,z=-40012..-25978\non x=34858..54746,y=55821..67805,z=-6400..20512\noff x=-82598..-70040,y=12999..27880,z=-28265..-20266\noff x=36133..50921,y=57912..79306,z=-24361..-3945\non x=21170..43558,y=59657..62681,z=32050..47374\noff x=67304..92012,y=-38797..-25594,z=-17070..-7453\non x=-24303..243,y=-38622..-26360,z=62498..80626\non x=-35395..-1784,y=55251..80783,z=-27257..-8718\non x=21761..37471,y=42320..78924,z=-57570..-23254\noff x=51236..81636,y=-15056..9704,z=-62783..-46651\non x=24630..41162,y=-80403..-63498,z=-31775..-7958\non x=-43744..-26951,y=47013..67420,z=-29814..-8102\non x=26603..63444,y=-12214..8779,z=65219..82907\non x=51940..73971,y=-74860..-42866,z=-13886..17671\non x=-92209..-76808,y=-10855..14855,z=7758..11786\noff x=8207..16724,y=-67090..-50961,z=43285..49532\non x=-22397..-2482,y=62603..88589,z=-33214..-12265\noff x=-78419..-62461,y=-21534..-2157,z=32108..35955\non x=-24006..12481,y=4449..20019,z=-81599..-71932\non x=-40605..-13896,y=-47711..-16365,z=-79247..-53064\non x=832..22273,y=61218..82893,z=-44519..-40600\noff x=-29620..-13977,y=-78751..-54304,z=27369..60011\noff x=-14823..10317,y=1880..32923,z=-92955..-62132\non x=54354..71905,y=-60825..-35957,z=-30727..-24555\non x=-33573..-29143,y=-72142..-60601,z=4256..27173\noff x=41652..57462,y=3208..37256,z=37000..58096\non x=-30305..-14266,y=-69805..-63510,z=-50479..-23537\non x=-49741..-29481,y=19079..42387,z=60359..80724\non x=10590..22833,y=44189..59294,z=46046..66234\noff x=27431..42490,y=-53997..-34542,z=52456..80995\non x=53221..89449,y=6997..28317,z=-44585..-14492\non x=46562..65116,y=-54532..-37162,z=-56535..-32017\non x=-42384..-12585,y=-70810..-55699,z=45949..64203\noff x=-77739..-63412,y=-41149..-14305,z=27531..47698\non x=10915..16415,y=-80296..-72044,z=-36080..-7043\noff x=64883..97215,y=8011..22409,z=-7746..5012\noff x=41971..72613,y=35196..51812,z=-44437..-29568\non x=-43711..-23568,y=-70680..-54720,z=-40601..-34077\non x=-16542..1141,y=52444..82672,z=43901..59221\noff x=-68205..-59516,y=-32644..-18560,z=38606..52974\noff x=61892..64269,y=-1452..11550,z=43516..54626\non x=-9354..26205,y=-95112..-73796,z=-12420..-2002\non x=68603..88449,y=14846..32027,z=-37275..-5964\noff x=53228..69887,y=-46772..-36524,z=4431..20570\noff x=-51220..-43549,y=22283..39913,z=-64534..-43506\non x=57172..86360,y=-26130..-709,z=16305..39032\non x=-17848..14974,y=46246..58941,z=-67550..-46292\noff x=58035..63691,y=14876..34764,z=26962..57547\non x=-75787..-51262,y=-49064..-29353,z=16029..20922\non x=-4894..14726,y=-17046..5889,z=72075..84493\noff x=33532..47782,y=-76307..-52222,z=-48923..-17755\non x=-13391..-5303,y=48245..70668,z=-63779..-49431\non x=-58930..-43507,y=20362..43248,z=48151..71704\non x=70149..92770,y=-9541..10644,z=-6635..28810\non x=31210..64948,y=-48006..-21611,z=40652..55871\non x=-28823..-7281,y=-9742..2492,z=-84876..-66370\noff x=-19166..-9817,y=-80319..-52505,z=-47786..-27118\non x=17602..27875,y=13530..26359,z=60440..89349\non x=-45705..-19841,y=56915..86069,z=4943..32642\noff x=68005..81155,y=-29765..-16005,z=-25061..-14900\non x=-65261..-52679,y=-66447..-52070,z=-28901..-7919\noff x=-82161..-52284,y=-22502..9835,z=-63314..-33804\noff x=-18121..-15979,y=-54614..-31740,z=51026..63778\non x=-73676..-54888,y=-49297..-18394,z=27558..33489\non x=28721..54510,y=-10030..-1313,z=-79605..-49555\noff x=11053..31767,y=-32845..-23247,z=-80025..-56672\non x=-68386..-51398,y=27015..53165,z=31825..40319\non x=-7540..22589,y=59320..83712,z=-35089..-28351\noff x=-48223..-32176,y=35378..59272,z=-66955..-50598\noff x=-25574..-10518,y=27418..50238,z=-79559..-52513\non x=19175..51407,y=-26995..-9229,z=66692..72533\noff x=-63090..-36602,y=16243..43718,z=46820..75911\non x=-18516..-9233,y=-48055..-28188,z=-82976..-60569\noff x=14759..44490,y=-10730..11648,z=56458..82559\non x=23455..40535,y=15420..27229,z=-84600..-55189\non x=-48299..-36541,y=50431..73592,z=-19144..-1839\noff x=-81344..-55459,y=20930..46304,z=-9345..14558\non x=47196..65637,y=30426..47615,z=-30228..-18046\noff x=58163..83551,y=8867..26974,z=-35873..-11226\noff x=6270..22771,y=37387..57400,z=36092..57663\noff x=-35106..-25204,y=5350..15478,z=63386..77636\non x=47160..59650,y=-51193..-34124,z=10503..49733\non x=-14789..3340,y=60278..88977,z=28007..46842\noff x=-6624..12635,y=-83183..-52792,z=29941..60688\noff x=-26353..-19359,y=-78917..-53187,z=-49653..-42601\non x=-87135..-47727,y=-10772..8106,z=35265..44532\non x=-88740..-70750,y=-217..15347,z=12806..29839\noff x=31685..53283,y=-81911..-47577,z=-25086..-18921\non x=46622..69887,y=-46629..-9525,z=-50166..-42612\noff x=-15867..1880,y=-98763..-72090,z=-5368..10111\non x=-5912..22381,y=9151..27498,z=-80071..-60569\non x=8192..15221,y=73577..81728,z=-14163..6679\non x=-22509..4222,y=20798..51576,z=55267..78530\non x=-7460..16881,y=51061..76312,z=31500..60109\noff x=63978..80332,y=-46479..-21214,z=17724..27861\noff x=-48142..-29566,y=-768..38252,z=63177..77599\noff x=-22352..-3879,y=-67212..-37828,z=-78347..-41751\non x=61414..72961,y=-45257..-28247,z=-33471..-19631\non x=-43526..-12688,y=57160..75937,z=9029..28586\non x=11342..37138,y=46690..59578,z=38233..55140\noff x=13515..32899,y=47202..66072,z=47086..57782\non x=-19540..6334,y=-56308..-35255,z=-77753..-55523\non x=-41925..-12451,y=61066..74500,z=-26647..-15757\non x=60674..79986,y=-30792..-1210,z=-57275..-33857\noff x=-18884..-7353,y=23448..56271,z=62374..74763\non x=-44386..-15359,y=25414..46534,z=-74182..-51840\non x=-36711..-20755,y=-67230..-45754,z=-56326..-24885\non x=72018..96153,y=16522..29738,z=-11147..2054\non x=5973..29852,y=55283..72301,z=42944..57136\noff x=-50246..-27653,y=49043..67875,z=24129..45399\non x=-22284..-17636,y=21285..32831,z=65161..74363\noff x=7735..17847,y=48454..54584,z=-60291..-42384\non x=56449..93303,y=12556..20734,z=-38611..-8545\noff x=46038..71965,y=-31095..-14977,z=-53091..-36186\non x=53210..73760,y=18116..23448,z=12649..33378\noff x=-83782..-50786,y=325..30910,z=36498..55449\non x=-11837..13836,y=-82566..-60770,z=-30914..-11896\noff x=-13846..7560,y=72..21588,z=68481..86336\noff x=-25685..-4603,y=29212..57708,z=-65410..-60517\noff x=-79113..-60386,y=26862..39241,z=-4353..29483\noff x=-19892..6771,y=51449..72628,z=23897..52090\noff x=-71828..-53222,y=28667..43977,z=25709..37388\noff x=28972..50612,y=2418..33040,z=-75017..-49640\noff x=-23546..3296,y=68489..75275,z=-33316..-23806\noff x=-46386..-30181,y=9760..24029,z=57523..67908\non x=-56316..-28882,y=48428..62993,z=-48907..-32191\non x=-19707..-779,y=-73947..-40756,z=31616..69601\noff x=37761..59827,y=6067..16149,z=51785..66336\non x=30923..48572,y=52049..72728,z=10493..16555\non x=-26538..1937,y=-81450..-56187,z=23729..60276\noff x=-48465..-28004,y=-90311..-56997,z=-9574..25785\noff x=-47377..-28389,y=-52506..-37609,z=-57507..-36160\non x=-36148..-24926,y=-64369..-51420,z=-69537..-38270\noff x=62650..82366,y=-28917..8638,z=44805..55648\noff x=28149..41757,y=43594..62806,z=-38133..-32521\non x=-60752..-36491,y=38751..61565,z=-48382..-36761\noff x=64344..99438,y=-18208..4375,z=-22825..-5665\non x=-51669..-27629,y=50339..66867,z=-41498..-24192\noff x=25105..40249,y=62440..67046,z=21785..49918\non x=58750..91053,y=10035..24848,z=-42659..-12650\noff x=-84762..-59511,y=-24289..1942,z=9634..24955\non x=-94933..-68735,y=-17490..292,z=-1579..16250\noff x=-78894..-60509,y=-40495..-5011,z=32059..62300\noff x=12870..39008,y=55250..64944,z=-51208..-30118\non x=2754..21460,y=-38868..-392,z=-93705..-64934\non x=18042..31269,y=-47061..-19873,z=52410..82608\non x=29379..68201,y=-51515..-41233,z=-45509..-34262\noff x=52671..84684,y=-16898..5471,z=37071..45630\non x=-98677..-68218,y=-20295..4826,z=5421..30729\noff x=-49508..-22500,y=-2847..20414,z=-69003..-50843\non x=-95062..-74272,y=9248..21864,z=-22635..1951\non x=7542..29789,y=-48009..-23225,z=-85578..-64378\noff x=-45405..-37229,y=-83374..-62547,z=-17446..8556\noff x=-45880..-27014,y=72506..92343,z=-5157..1778\non x=-30115..-2925,y=52679..71889,z=-68644..-48667\non x=-76029..-52766,y=-54478..-30765,z=-46175..-28566"
  )
}

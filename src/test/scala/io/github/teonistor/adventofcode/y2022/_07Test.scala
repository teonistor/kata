package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase
import io.github.teonistor.adventofcode.y2022._07.{Dir, File}

class _07Test extends AdventOfCodeTestBase {
  testAndRun(_07,
    "$ cd /\n$ ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d\n$ cd a\n$ ls\ndir e\n29116 f\n2557 g\n62596 h.lst\n$ cd e\n$ ls\n584 i\n$ cd ..\n$ cd ..\n$ cd d\n$ ls\n4060174 j\n8033020 d.log\n5626152 d.ext\n7214296 k",
    95437,
    24933642,
    "$ cd /\n$ ls\ndir dpbwg\ndir dvwfscw\ndir hccpl\ndir jsgbg\ndir lhjmzsl\n63532 mwvbpw.mmg\n239480 npj\ndir pngs\ndir qhs\n303649 shvgmwn.vhv\n236905 sjrrgd.phh\ndir sntcp\ndir sqs\n$ cd dpbwg\n$ ls\ndir dgh\n100731 dpbwg\ndir rpwnv\n$ cd dgh\n$ ls\n197049 lhjmzsl.hzj\n$ cd ..\n$ cd rpwnv\n$ ls\n10702 qsgv.fmf\n$ cd ..\n$ cd ..\n$ cd dvwfscw\n$ ls\ndir bvg\ndir fbfjs\n115450 gjftb.mgd\ndir gsmnprgz\ndir hdwdcvv\ndir mhjtrlqz\n75437 qsctddrw\n171722 qsgv.zqz\n$ cd bvg\n$ ls\n56335 cgtzb.szt\n139481 shvgmwn.vhv\n255200 wzqlgr.mhl\n$ cd ..\n$ cd fbfjs\n$ ls\n252977 hmcj\n256083 mbgfn.pmh\ndir qsgv\n$ cd qsgv\n$ ls\n271506 dchsdfz.bbg\n202650 hmcj\n32623 lqgmfcp\n57614 mgp.fbn\n220895 qwzqrrq.wjf\n$ cd ..\n$ cd ..\n$ cd gsmnprgz\n$ ls\ndir dzcsldzw\ndir hrjmfd\ndir lcwv\ndir sdp\n62355 zrncdmd.lmj\n$ cd dzcsldzw\n$ ls\ndir hmcj\n$ cd hmcj\n$ ls\n151947 hgtzldbg\n$ cd ..\n$ cd ..\n$ cd hrjmfd\n$ ls\ndir drjlhbqf\n65599 npj\n$ cd drjlhbqf\n$ ls\n263623 hzfmzs.mlj\n13866 npj\n173713 wtnf.qps\n$ cd ..\n$ cd ..\n$ cd lcwv\n$ ls\n44150 hmcj.lds\n200694 mpbb\n$ cd ..\n$ cd sdp\n$ ls\ndir gcwjj\ndir qzdczvwn\n$ cd gcwjj\n$ ls\n149603 qsgv.srr\n$ cd ..\n$ cd qzdczvwn\n$ ls\ndir gnvbm\n291187 hmcj.rgm\ndir msdt\ndir mwvbpw\ndir shpr\n$ cd gnvbm\n$ ls\n259516 dpbwg\n120868 mpbb\ndir vqrcd\n$ cd vqrcd\n$ ls\n306804 fqqg\n34290 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd msdt\n$ ls\n168438 mpbb\n67435 nbqcrdjs\n$ cd ..\n$ cd mwvbpw\n$ ls\ndir btzqzvbl\n308719 npj\n$ cd btzqzvbl\n$ ls\n177311 bdnrf.jtw\n122356 qwhmd.vcd\n169153 vzzzccg.hlb\n$ cd ..\n$ cd ..\n$ cd shpr\n$ ls\n290591 nblzc.nmp\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd hdwdcvv\n$ ls\n96864 hmcj.tjn\n$ cd ..\n$ cd mhjtrlqz\n$ ls\ndir bzrbsfjp\n238772 fpggqqj\ndir hqzgs\n20155 shvgmwn.vhv\n$ cd bzrbsfjp\n$ ls\n313691 fnscbhfc\n17630 llwfdzgg.bsp\ndir lthr\n$ cd lthr\n$ ls\n237053 bhbbzt.bmt\n$ cd ..\n$ cd ..\n$ cd hqzgs\n$ ls\n295258 gllsgr.nnz\n70743 ptpqd\ndir rnmsdpmj\n205022 rpqh.rpn\n158287 tsm.tdq\n154025 wmfwr.bcm\n$ cd rnmsdpmj\n$ ls\n218043 dpbwg.mls\n149072 mbgfn.pmh\n89388 mwvbpw.qfm\n57207 rszcvm.mqc\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd hccpl\n$ ls\ndir blqdjh\ndir hfqw\ndir qsgv\n$ cd blqdjh\n$ ls\ndir fbpg\n$ cd fbpg\n$ ls\n231357 dpbwg\n$ cd ..\n$ cd ..\n$ cd hfqw\n$ ls\n210898 fjblghm.gtg\n286252 hmcj.dgz\n258768 mpbb\n225743 qsgv.pqf\n191717 shvgmwn.vhv\n$ cd ..\n$ cd qsgv\n$ ls\ndir bslqpr\ndir dpbwg\ndir lhjmzsl\n210707 npj\n64435 qhpnrbhq\ndir rpj\n301426 shvgmwn.vhv\ndir stbgbrw\ndir vhzmg\n$ cd bslqpr\n$ ls\n4365 dpbwg\ndir fpj\ndir lhjmzsl\ndir lncmt\ndir mwvbpw\n35725 ncbjtpcb.svf\ndir njhsmb\ndir rjzsddlw\n41533 shvgmwn.vhv\n$ cd fpj\n$ ls\n88146 dpbwg\n$ cd ..\n$ cd lhjmzsl\n$ ls\n57400 fzngdsh.sbn\n198711 mnqz.npt\n$ cd ..\n$ cd lncmt\n$ ls\n192228 qsgv.jss\n$ cd ..\n$ cd mwvbpw\n$ ls\n65217 hmcj.drs\n$ cd ..\n$ cd njhsmb\n$ ls\n157177 dpbwg.wsl\n10919 jhfs\n$ cd ..\n$ cd rjzsddlw\n$ ls\ndir cftp\ndir flcfwml\ndir vpdbl\n$ cd cftp\n$ ls\n89075 dpbwg\n57259 mbgfn.pmh\n237771 zwglrhh\n$ cd ..\n$ cd flcfwml\n$ ls\n51498 pbbgmqn.gfg\n$ cd ..\n$ cd vpdbl\n$ ls\n98690 npj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd dpbwg\n$ ls\ndir wlmdbjh\n$ cd wlmdbjh\n$ ls\ndir pmldd\n245468 rdgldw.tzb\n$ cd pmldd\n$ ls\n145032 mwvbpw.tpc\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd lhjmzsl\n$ ls\n233642 dpbwg.tbq\ndir frsggjl\ndir hmcj\ndir jmhzlq\ndir jvdtpzd\n251765 npj\n169647 shvgmwn.vhv\n$ cd frsggjl\n$ ls\ndir dshv\ndir fqvpc\ndir lhjmzsl\n313045 npj\ndir rngqmwgr\n$ cd dshv\n$ ls\ndir pptfqn\n85609 wpgz\ndir zfmqls\n$ cd pptfqn\n$ ls\n258817 mtctcdgd.nmb\n39899 npj\n$ cd ..\n$ cd zfmqls\n$ ls\n119006 hlw.mzg\n295107 wstvdqn.wgw\n$ cd ..\n$ cd ..\n$ cd fqvpc\n$ ls\ndir pqpf\n$ cd pqpf\n$ ls\n312566 mpbb\n$ cd ..\n$ cd ..\n$ cd lhjmzsl\n$ ls\n157261 dpbwg.bgc\n76700 mpbb\n$ cd ..\n$ cd rngqmwgr\n$ ls\n42626 dpbwg.dtt\n78765 gjsnmzn.fzb\n$ cd ..\n$ cd ..\n$ cd hmcj\n$ ls\n166183 jntzn\ndir qsgv\n254851 rbcgrdr.vqp\n$ cd qsgv\n$ ls\n256449 dhj.mrm\n49207 fbrhl\n69922 lhjmzsl\n121778 tpdvnb\n$ cd ..\n$ cd ..\n$ cd jmhzlq\n$ ls\ndir bbpqsf\n245813 ftw.jwq\n$ cd bbpqsf\n$ ls\n169373 mwvbpw.tjt\n$ cd ..\n$ cd ..\n$ cd jvdtpzd\n$ ls\ndir brfln\ndir dpbwg\n263586 fsqzfhj.bzh\n85956 lhjmzsl\ndir mwvbpw\ndir tcgwhp\n91473 vjgt.twz\n200413 zlnnrrpn.qqd\n$ cd brfln\n$ ls\n65066 fpvnm\n$ cd ..\n$ cd dpbwg\n$ ls\ndir jtqwgc\ndir lrdjdqn\n281885 mpbb\n$ cd jtqwgc\n$ ls\n219022 spbqn\n$ cd ..\n$ cd lrdjdqn\n$ ls\n116830 hmcj.ptr\ndir mwvbpw\n$ cd mwvbpw\n$ ls\n251737 ccqlb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd mwvbpw\n$ ls\n226329 fztjpfb\n$ cd ..\n$ cd tcgwhp\n$ ls\ndir grmsl\n$ cd grmsl\n$ ls\ndir hmcj\n197995 wzqlr.fqj\n$ cd hmcj\n$ ls\n239775 mbgfn.pmh\ndir rrvccjp\n95381 shvgmwn.vhv\n$ cd rrvccjp\n$ ls\ndir qsgv\n$ cd qsgv\n$ ls\n192956 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rpj\n$ ls\ndir bwc\ndir ctjwjlzc\ndir dpbwg\ndir lhjmzsl\n249767 npj\ndir wqmlz\n$ cd bwc\n$ ls\n56181 hmcj.dhd\n105111 hvw\n63869 rqw.srq\n8030 shvgmwn.vhv\ndir stff\n$ cd stff\n$ ls\ndir gsjsc\n$ cd gsjsc\n$ ls\n514 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ctjwjlzc\n$ ls\n39823 qjgnjm.hzn\n$ cd ..\n$ cd dpbwg\n$ ls\ndir lhqrjr\n$ cd lhqrjr\n$ ls\n179949 tmvl.zqf\n$ cd ..\n$ cd ..\n$ cd lhjmzsl\n$ ls\n305735 shvgmwn.vhv\n$ cd ..\n$ cd wqmlz\n$ ls\n141757 fstftggh\n$ cd ..\n$ cd ..\n$ cd stbgbrw\n$ ls\n196003 dpbwg.shs\ndir gbr\n104091 hmcj\n232145 lhjmzsl.nnc\ndir lhv\ndir qqqbtpq\n51208 qsgv.dbm\n235090 vbpzgnr\ndir vqmnsdrt\n$ cd gbr\n$ ls\n101809 npj\n121922 pcqrmmlt.ghh\n125915 ptffhc\n128293 wdz.nsd\n$ cd ..\n$ cd lhv\n$ ls\n75506 qtwlnvv.nbm\n28413 rbwbp\n$ cd ..\n$ cd qqqbtpq\n$ ls\n96300 gdf\n$ cd ..\n$ cd vqmnsdrt\n$ ls\n146229 mwvbpw.qrc\n189540 plldv.vtv\ndir rtng\n$ cd rtng\n$ ls\n42730 hrbs.zpc\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd vhzmg\n$ ls\ndir dcmjvhtt\ndir dpbwg\n$ cd dcmjvhtt\n$ ls\n85508 hhlctr.bbs\n296657 lhjmzsl.zjt\n255803 mbgfn.pmh\n170803 mtctcdgd.nmb\n$ cd ..\n$ cd dpbwg\n$ ls\n156142 hmcj\ndir lhjmzsl\n$ cd lhjmzsl\n$ ls\n13590 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd jsgbg\n$ ls\n202682 hmcj.tbl\n$ cd ..\n$ cd lhjmzsl\n$ ls\n197308 lhjmzsl\n$ cd ..\n$ cd pngs\n$ ls\n115979 hfpzqf.hjw\ndir lhjmzsl\n125414 mpbb\ndir mwbffchr\n$ cd lhjmzsl\n$ ls\n55108 hwhsjr\n$ cd ..\n$ cd mwbffchr\n$ ls\n249138 czv.lgd\n$ cd ..\n$ cd ..\n$ cd qhs\n$ ls\n108106 ccmhjzp.ppf\n197911 shvgmwn.vhv\n$ cd ..\n$ cd sntcp\n$ ls\ndir cvgtwsbw\ndir dpbwg\ndir dqbzfcq\ndir gfvtsjmz\ndir ljsmjsp\ndir mwvbpw\ndir phfqzwp\ndir rpsnfndl\ndir rrqwcbqm\ndir rtv\ndir vnvft\ndir vqcvbncp\ndir vsgjds\n$ cd cvgtwsbw\n$ ls\ndir bprbr\ndir bpw\ndir dpbwg\ndir jvjs\ndir mwvbpw\ndir nbfzn\ndir pcr\ndir rhgltw\n$ cd bprbr\n$ ls\n126107 dpbwg.jqq\n140931 ljhc.gsm\n120326 pnjv\ndir wbrb\n$ cd wbrb\n$ ls\n28887 ccr.rvd\ndir gwfpgws\n77898 mwvbpw.qrc\n280487 pbfbtb.qjp\n$ cd gwfpgws\n$ ls\n205904 dgm\n313203 vpgzr.jfw\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd bpw\n$ ls\ndir lgstpgrn\n173334 npj\n$ cd lgstpgrn\n$ ls\ndir rngr\n$ cd rngr\n$ ls\n291856 czjz.fzp\n246123 mpbb\n200301 qqfvpnz\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd dpbwg\n$ ls\n265739 mtctcdgd.nmb\ndir mwvbpw\n76505 ntjn.mnp\n227157 pqznms\n312879 qzlmsht\n$ cd mwvbpw\n$ ls\n144523 dpbwg.lgr\n39441 mmtrnzqw.mnv\n285772 mpbb\n306936 rpmgcmqd.qht\n$ cd ..\n$ cd ..\n$ cd jvjs\n$ ls\n51134 lsnvhd.gsj\n$ cd ..\n$ cd mwvbpw\n$ ls\n125428 hmcj.bvd\n40162 npj\n117658 pmzfj.crj\n$ cd ..\n$ cd nbfzn\n$ ls\n11429 mwvbpw.swl\n5582 qsgv.jrm\n$ cd ..\n$ cd pcr\n$ ls\ndir dpbwg\n95858 mtctcdgd.nmb\n$ cd dpbwg\n$ ls\ndir wmsb\n$ cd wmsb\n$ ls\n154498 npj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rhgltw\n$ ls\n188447 qsgv.rzs\n$ cd ..\n$ cd ..\n$ cd dpbwg\n$ ls\n261326 cjfszwr\n88528 glp.fgc\ndir lhjmzsl\n270470 shvgmwn.vhv\n310081 vdhmgb\ndir wsc\n$ cd lhjmzsl\n$ ls\n245410 dnnfljbs\n$ cd ..\n$ cd wsc\n$ ls\ndir lhjmzsl\n311302 mbs.tqc\n309985 mpbb\ndir qgpqzr\n20812 vhcc.wpw\n$ cd lhjmzsl\n$ ls\ndir dpbwg\n$ cd dpbwg\n$ ls\n273340 fmvbcphl.crp\n$ cd ..\n$ cd ..\n$ cd qgpqzr\n$ ls\ndir fdwgdhrf\n$ cd fdwgdhrf\n$ ls\n275421 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd dqbzfcq\n$ ls\n30930 njnz\n$ cd ..\n$ cd gfvtsjmz\n$ ls\n290134 mbgfn.pmh\n154689 vql\n$ cd ..\n$ cd ljsmjsp\n$ ls\n59894 hwggw.rpd\n26336 mbgfn.pmh\ndir mvzchpq\ndir mwvbpw\n122061 zlcr.jsw\n$ cd mvzchpq\n$ ls\n294618 lhjmzsl.bqm\ndir mwvbpw\n204294 nljhbh.cfc\n79337 ntvt.dbw\n$ cd mwvbpw\n$ ls\ndir njwcqcjh\n309971 nqdv.vmp\n$ cd njwcqcjh\n$ ls\n155159 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd mwvbpw\n$ ls\n43451 lhjmzsl.vjt\n$ cd ..\n$ cd ..\n$ cd mwvbpw\n$ ls\n3144 phvjzs.prf\n$ cd ..\n$ cd phfqzwp\n$ ls\ndir hmcj\ndir hnnrw\n196279 lhjmzsl\n90948 mwvbpw.hvm\n275082 shvgmwn.vhv\n$ cd hmcj\n$ ls\n4346 mtctcdgd.nmb\n$ cd ..\n$ cd hnnrw\n$ ls\ndir lhjmzsl\n103070 qsbrf.tpv\ndir rdf\n$ cd lhjmzsl\n$ ls\n158692 dpbwg.bvl\n$ cd ..\n$ cd rdf\n$ ls\ndir gpj\ndir lbgpm\n$ cd gpj\n$ ls\n136508 shvgmwn.vhv\n$ cd ..\n$ cd lbgpm\n$ ls\n154457 ccpvr\n232065 lhjmzsl\n43787 npj\n189920 rpgmvv\n96362 tlmvpc\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rpsnfndl\n$ ls\ndir pcbtmcwf\n$ cd pcbtmcwf\n$ ls\ndir qsgv\n$ cd qsgv\n$ ls\n45662 npj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rrqwcbqm\n$ ls\ndir vjm\n$ cd vjm\n$ ls\n263137 npj\n$ cd ..\n$ cd ..\n$ cd rtv\n$ ls\ndir lhjmzsl\ndir pcwsn\ndir qsgv\ndir vvzbsg\n$ cd lhjmzsl\n$ ls\ndir qzsjhsfn\n$ cd qzsjhsfn\n$ ls\n26909 mbgfn.pmh\n$ cd ..\n$ cd ..\n$ cd pcwsn\n$ ls\n160033 ftz.nbg\n145165 wgdjsgsb.hhm\n$ cd ..\n$ cd qsgv\n$ ls\ndir jqm\n136209 mbgfn.pmh\n79664 mghdlnh.chv\n$ cd jqm\n$ ls\n222831 npj\n$ cd ..\n$ cd ..\n$ cd vvzbsg\n$ ls\n84246 dpbwg.tsq\n$ cd ..\n$ cd ..\n$ cd vnvft\n$ ls\ndir crqrl\ndir scdvzsd\n$ cd crqrl\n$ ls\n13185 dpbwg.twz\n109021 qcdqnhg.jjj\n$ cd ..\n$ cd scdvzsd\n$ ls\ndir hmcj\n$ cd hmcj\n$ ls\n252155 npj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd vqcvbncp\n$ ls\n72128 crnhb\ndir ddz\ndir dvhzvdn\ndir ffsbcmg\ndir gfs\ndir jghlb\ndir llq\ndir pvn\ndir rvqmmjgr\n$ cd ddz\n$ ls\n232302 gdfqpnj.nhf\n274940 lhjmzsl.mzv\n286217 mpbb\n$ cd ..\n$ cd dvhzvdn\n$ ls\n273183 cfnqn.qpz\n$ cd ..\n$ cd ffsbcmg\n$ ls\n6544 mtctcdgd.nmb\n52511 wndzt.lqb\n$ cd ..\n$ cd gfs\n$ ls\n228312 mwvbpw.tdj\ndir rqjrfzgt\n62100 shc.rtj\n313984 szsplzq.dpd\ndir tqtnp\n$ cd rqjrfzgt\n$ ls\n296460 bvp\n272324 mbgfn.pmh\n221044 npj\n110232 qvcszzv\n$ cd ..\n$ cd tqtnp\n$ ls\n98376 tzwh\n212247 wzjj.scp\n$ cd ..\n$ cd ..\n$ cd jghlb\n$ ls\ndir ddrmsv\n$ cd ddrmsv\n$ ls\n252143 mbgfn.pmh\n$ cd ..\n$ cd ..\n$ cd llq\n$ ls\ndir lhjmzsl\n67341 mbgfn.pmh\n155159 npj\ndir qsgv\ndir vcvnzc\n$ cd lhjmzsl\n$ ls\n151930 ssldrsf.mzm\n$ cd ..\n$ cd qsgv\n$ ls\n233702 ztdmtqb.mhf\n$ cd ..\n$ cd vcvnzc\n$ ls\n168179 lhjmzsl.cwf\n$ cd ..\n$ cd ..\n$ cd pvn\n$ ls\n240160 lbd.slf\n$ cd ..\n$ cd rvqmmjgr\n$ ls\n24809 gwgcrzp.zhj\ndir hmcj\ndir lhjmzsl\ndir mwvbpw\n121997 nbmf\n77886 ntp\ndir qcqrgdm\n$ cd hmcj\n$ ls\n126073 dpbwg.lhh\ndir jchccghv\ndir lhjmzsl\n144075 qsgv.lbw\n288076 swrngbsq.qzw\n$ cd jchccghv\n$ ls\n310277 mpbb\n$ cd ..\n$ cd lhjmzsl\n$ ls\n193576 bwsz.crv\n16002 mbgfn.pmh\n57961 mtctcdgd.nmb\n$ cd ..\n$ cd ..\n$ cd lhjmzsl\n$ ls\ndir bsfwmcz\ndir dpbwg\n121640 mwvbpw\ndir pmrfvzw\ndir qsgv\n$ cd bsfwmcz\n$ ls\ndir fjqqblz\n51952 gqbj.fwd\ndir hmcj\ndir lctt\n81316 lhjmzsl.qqr\n6372 mbgfn.pmh\n22669 shvgmwn.vhv\n130990 zgq.fpv\n$ cd fjqqblz\n$ ls\n26064 zrqrnb.nwf\n$ cd ..\n$ cd hmcj\n$ ls\n14385 mwvbpw.vzq\n$ cd ..\n$ cd lctt\n$ ls\n137397 fbgbs.fjf\ndir jgbzqzq\ndir qrr\n272639 qsgv.vwm\n$ cd jgbzqzq\n$ ls\n135320 mpbb\n$ cd ..\n$ cd qrr\n$ ls\ndir dpbwg\ndir mwvbpw\n54143 pnsl.bbm\ndir wbpzpg\n$ cd dpbwg\n$ ls\n250653 shvgmwn.vhv\n$ cd ..\n$ cd mwvbpw\n$ ls\n2682 fhdh.hmt\n$ cd ..\n$ cd wbpzpg\n$ ls\ndir bqshqpvm\n141398 mtctcdgd.nmb\n130729 vtnfmjqm.mgp\n$ cd bqshqpvm\n$ ls\n252449 shvgmwn.vhv\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd dpbwg\n$ ls\n82216 gwjwmcbf.gwl\n$ cd ..\n$ cd pmrfvzw\n$ ls\ndir mwvbpw\ndir wtzgjspz\ndir zmb\n$ cd mwvbpw\n$ ls\n16736 wbzjzz.mqq\n$ cd ..\n$ cd wtzgjspz\n$ ls\ndir vjb\n$ cd vjb\n$ ls\n301763 drchphtm\n$ cd ..\n$ cd ..\n$ cd zmb\n$ ls\n201978 mdj\ndir qgnffw\n147889 qzbmwpnm.gjp\n$ cd qgnffw\n$ ls\n17740 cwz.dhs\n222345 mbgfn.pmh\n81554 npj\n206597 tsnlnzh\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd qsgv\n$ ls\ndir mmsnzr\n$ cd mmsnzr\n$ ls\n160502 tgrztm\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd mwvbpw\n$ ls\n40049 qmmdv\n313418 qsgv.srp\n$ cd ..\n$ cd qcqrgdm\n$ ls\n202848 wtl.qbj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd vsgjds\n$ ls\n138690 npj\n22984 tpfbnz.sgj\n$ cd ..\n$ cd ..\n$ cd sqs\n$ ls\ndir zms\n$ cd zms\n$ ls\n152096 cvtqph.wwp\ndir mmwzg\n$ cd mmwzg\n$ ls\n100870 qsgv"
  )

  test("recursiveSet") {
    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val (actualOuter, actualParentDirs) = _07.recursiveSet(newInner, Some(inputMiddle), Map(inputMiddle -> inputOuter))
    assert(actualOuter == expectedOuter)
    assert(actualParentDirs == Map(newInner -> expectedMiddle, expectedMiddle -> expectedOuter))
  }

  test("parseFs - dir in root") {
    val input = Dir(List.empty, Map.empty, Map.empty)

    val newInner = Dir(List("a"), Map.empty, Map.empty)
    val expectedOuter = Dir(List.empty, Map("a" -> newInner), Map.empty)

    val actual = _07.parseFs(List("dir a"), input, currentDir = Some(input))
    assert(actual == expectedOuter)
  }

  test("parseFs - defaults") {
    val newInner = Dir(List("a"), Map.empty, Map.empty)
    val expectedOuter = Dir(List.empty, Map("a" -> newInner), Map.empty)

    val actual = _07.parseFs(List("$ cd /","dir a"))
    assert(actual == expectedOuter)
  }

  test("parseFs - dir deeper") {
    val inputInner = Dir(List("b"), Map.empty, Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputInner), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List("dir a"), inputOuter, Map(inputInner -> inputOuter), Some(inputInner))
    assert(actual == expectedOuter)
  }

  test("parseFs - file") {
    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List("77 c.txt"), inputOuter, Map(inputInner -> inputMiddle, inputMiddle -> inputOuter), Some(inputInner))
    assert(actual == expectedOuter)
  }

  test("parseFs - multi") {
//    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
//    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
//    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val expectedInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> expectedInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List(
      "$ cd /",
      "$ ls",
      "dir b",
      "$ cd b",
      "$ ls",
      "dir a",
      "$ cd a",
      "$ ls",
      "77 c.txt"))
    assert(actual == expectedOuter)
  }

  test("parseFs - extraneous cd") {
    //    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
    //    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
    //    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val expectedInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> expectedInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List(
      "$ cd /",
      "$ ls",
      "dir b",
      "$ cd /",
      "$ cd b",
      "$ ls",
      "dir a",
      "$ cd a",
      "$ cd ..",
      "$ cd a",
      "$ ls",
      "77 c.txt"))
    assert(actual == expectedOuter)
  }
}

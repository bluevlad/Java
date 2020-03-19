<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE>Network Throughput: Local Hosts - Data Received</TITLE>
<META HTTP-EQUIV=REFRESH CONTENT=120>
<META HTTP-EQUIV=Pragma CONTENT=no-cache>
<META HTTP-EQUIV=Cache-Control CONTENT=no-cache>
<link rel="stylesheet" href="theme.css" TYPE="text/css">
<script type="text/javascript" language="JavaScript" SRC="theme.js"></script>
<script type="text/javascript" language="JavaScript" SRC="JSCookMenu.js"></script>
<script type="text/javascript" language="JavaScript"><!--
var ntopMenu =
[
    [null,'About',null,null,null,
        [null,'What is ntop?','/aboutNtop.html',null,null],
        [null,'Show Configuration','/info.html',null,null],
        [null,'Credits','/Credits.html',null,null],
        [null,'Man Page','/ntop.html',null,null],
        ['<img src="/help.png">','Help','/ntophelp.html',null,null],
        ['<img src="/bug.png">','Report a Problem','/ntopProblemReport.html',null,null],
        [null,'FAQ','/faq.html',null,null],
        ['<img src="/Risk_high.gif">','Risk Flags','/help.html',null,null],
        ],
    [null,'Summary',null,null,null,
        [null,'Traffic','/trafficStats.html',null,null],
        [null,'Hosts','/hostsInfo.html',null,null],
        [null,'Network Load','/thptStats.html',null,null],
        [null,'Network Flows','/NetFlows.html',null,null],
        ],
  [null,'All Protocols',null,null,null,
          [null,'Traffic','/sortDataProtos.html',null,null],
          [null,'Throughput','/sortDataThpt.html',null,null],
          [null,'Activity','/dataHostTraffic.html',null,null],
          ],
    [null,'IP',null,null,null,
        [null,'Summary',null,null,null,
                [null,'Traffic','/sortDataIP.html',null,null],
                [null,'Multicast','/multicastStats.html',null,null],
                [null,'Internet Domain','/domainStats.html',null,null],
                [null,'Host Clusters','/hostClusters.html',null,null],
                [null,'Distribution','/ipProtoDistrib.html',null,null],
        ],
        [null,'Traffic Directions',null,null,null,
                [null,'Local to Local','/ipL2L.html',null,null],
                [null,'Local to Remote','/ipL2R.html',null,null],
                [null,'Remote to Local','/ipR2L.html',null,null],
                [null,'Remote to Remote','/ipR2R.html',null,null],
        ],
        [null,'Local',null,null,null,
                [null,'Ports Used','/ipProtoUsage.html',null,null],
                [null,'Active TCP/UDP Sessions','/NetNetstat.html',null,null],
                [null,'Host Fingerprint','/localHostsFingerprint.html',null,null],
                [null,'Host Characterization','/localHostsCharacterization.html',null,null],
                [null,'Network Traffic Map','/networkMap.html',null,null],
                [null,'Local Matrix','/ipTrafficMatrix.html',null,null],
        ],
    ],
    [null,'Media',null,null,null,
        [null,'Fibre Channel',null,null,null,
                [null,'Traffic','/fcData.html',null,null],
                [null,'Throughput','/fcThpt.html',null,null],
                [null,'Activity','/fcActivity.html',null,null],
                [null,'Hosts','/fcHostsInfo.html',null,null],
                [null,'Traffic Per Port','/fcShowStats.html',null,null],
                [null,'Sessions','/FcSessions.html',null,null],
                [null,'VSANs','/vsanList.html',null,null],
                [null,'VSAN Summary','/vsanDistrib.html',null,null],
        ],
        [null,'SCSI Sessions',null,null,null,
                [null,'Bytes','/ScsiBytes.html',null,null],
                [null,'Times','/ScsiTimes.html',null,null],
                [null,'Status','/ScsiStatus.html',null,null],
                [null,'Task Management','/ScsiTMInfo.html',null,null],
        ],
    ],
    [null,'Utils',null,null,null,
        [null,'Data Dump','/dump.html',null,null],
        [null,'View Log','/viewLog.html',null,null],
        ],
    [null,'Plugins',null,null,null,
        [null,'Host Last Seen',null,null,null,
                [null,'Activate','/showPlugins.html?LastSeen=1',null,null],
                [null,'Describe','/showPlugins.html?LastSeen',null,null],
             ],
        [null,'ICMP Watch',null,null,null,
                [null,'Activate','/showPlugins.html?icmpWatch=1',null,null],
                [null,'Describe','/showPlugins.html?icmpWatch',null,null],
             ],
        [null,'NetFlow',null,null,null,
                [null,'Activate','/showPlugins.html?NetFlow=1',null,null],
                [null,'Configure','/plugins/NetFlow',null,null],
                [null,'Describe','/showPlugins.html?NetFlow',null,null],
                [null,'Statistics','/plugins/NetFlow/statistics.html',null,null],
             ],
        [null,'PDA',null,null,null,
                [null,'Activate','/showPlugins.html?PDAPlugin=1',null,null],
                [null,'Describe','/showPlugins.html?PDAPlugin',null,null],
             ],
        [null,'Round-Robin Databases',null,null,null,
                [null,'Deactivate','/showPlugins.html?rrdPlugin=0',null,null],
                [null,'Configure','/plugins/rrdPlugin',null,null],
                [null,'Describe','/showPlugins.html?rrdPlugin',null,null],
                [null,'Statistics','/plugins/rrdPlugin/statistics.html',null,null],
                ['<img src="/graph.gif">','Arbitrary Graphs','/plugins/rrdPlugin/arbgraph.html',null,null],
             ],
        [null,'sFlow',null,null,null,
                [null,'Activate','/showPlugins.html?sFlow=1',null,null],
                [null,'View/Configure','/plugins/sFlow',null,null],
                [null,'Describe','/showPlugins.html?sFlow',null,null],
             ],
        [null,'SNMP',null,null,null,
                [null,'Activate','/showPlugins.html?snmpPlugin=1',null,null],
                [null,'Configure','/plugins/snmpPlugin',null,null],
                [null,'Describe','/showPlugins.html?snmpPlugin',null,null],
             ],
        [null,'XML data dump',null,null,null,
                [null,'Activate','/showPlugins.html?xmldump=1',null,null],
                [null,'Configure','/plugins/xmldump',null,null],
                [null,'Describe','/showPlugins.html?xmldump',null,null],
                [null,'Dump','/plugins/xmldump/dump.xml',null,null],
             ],
        [null,'All','/showPlugins.html',null,null],
        ],
    [null,'Admin',null,null,null,
        ['<img src="/lock.png">','Configure',null,null,null,
            ['<img src="/lock.png">','Startup Options','/configNtop.html',null,null],
            ['<img src="/lock.png">','Preferences','/editPrefs.html',null,null],
            ['<img src="/lock.png">','Packet Filter','/changeFilter.html',null,null],
            ['<img src="/lock.png">','Reset Stats','/resetStats.html',null,null],
            ['<img src="/lock.png">','Web Users','/showUsers.html',null,null],
            ['<img src="/lock.png">','Protect URLs','/showURLs.html',null,null],
        ],
        ['<img src="/lock.png">','Shutdown','/shutdown.html',null,null],
    ]
];
--></script>
</head><body link="blue" vlink="blue">
<table border="0" width="100%" cellpadding="0" cellspacing="0">

 <tr>
  <th class="leftmenuitem">
   <div id="ntopMenuID">Rut Row - bad mojo Scooby!</div>
<script type="text/javascript" language="JavaScript"><!--
        cmDraw ('ntopMenuID', ntopMenu, 'hbr', cmThemeOffice, 'ThemeOffice');
-->
</script></th>

 </tr>
</table>
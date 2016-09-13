//	code	,	text

//code:--	"4"代表字符串，4代表整型
//出票方式
var issueType = [//int	pnrorder.airlineIssueticketType  = '4'	
    {code:0,  text:"所有"}, 
	{code:4	,  text:"本票通半自动出票"}, 
	{code:5	,  text:"汇付半自动出票"},
	{code:18,  text:"易航宝半自动出票"},
	{code:9	,  text:"本票通全自动出票"},
	{code:10,  text:"汇付全自动出票"},
	{code:19,  text:"易航宝全自动出票"},
	{code:6	,  text:"财付通半自动出票"},
	{code:11,  text:"财付通全自动出票"},
	{code:20,  text:"御航宝半自动出票"},
	{code:21,  text:"御航宝全自动出票"},
	{code:8	,  text:"BSP自动出票"},
	{code:3	,  text:"手工出票"}
]

//政策类型
var isSpecialPolicyType = [//integer pnrorder.isSpecialPolicyType = 1
    {code:0 , text:"全部政策"},
	{code:1 , text:"特殊政策"},
	{code:2 , text:"普通政策"},
	{code:3 , text:"特惠政策"}	
]           

//航空公司
var airlineCode = [//string pnrorder.airlineFlightNo like '3U%'
    {code:"  " , text:"全部"},
    {code:"3U" , text:"3U四川航空"},
	{code:"8C" , text:"8C东星航空"},
	{code:"8L" , text:"8L祥鹏航空"},
	{code:"9C" , text:"9C春秋航空"},
	{code:"BK" , text:"BK奥凯航空"},
	{code:"CA" , text:"CA国际航空"},
	{code:"CN" , text:"CN大新华航空"},
	{code:"CZ" , text:"CZ南方航空"},
	{code:"EU" , text:"EU成都航空"},
	{code:"FM" , text:"FM上海航空"},
	{code:"G5" , text:"G5华夏航空"},
	{code:"GS" , text:"GS天津航空"},
	{code:"HO" , text:"HO吉祥航空"},
	{code:"HU" , text:"HU海南航空"},
	{code:"JD" , text:"JD首都航空"},
	{code:"JR" , text:"JR幸福航空"},
	{code:"KN" , text:"KN联合航空"},
	{code:"KY" , text:"KY昆明航空"},
	{code:"MF" , text:"MF厦门航空"},
	{code:"MU" , text:"MU东方航空"},
	{code:"NS" , text:"NS河北航空"},
	{code:"OQ" , text:"OQ重庆航空"},
	{code:"PN" , text:"PN西部航空"},
	{code:"SC" , text:"SC山东航空"},
	{code:"TV" , text:"TV西藏航空公司"},
	{code:"VD" , text:"VD鲲鹏航空"},
	{code:"ZH" , text:"ZH深圳航空"}
]
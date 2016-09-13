//function Gui(){};
Gui = {};
/**
 * 初始化下拉列表
 * 
 * @param selectList	定义的下拉列表json
 * @param selectId		下拉列表id
 * @param entityFiled	用于回显的表达式
 * @param dufaultName	默认全选名称
 * @returns
 */
/*Gui.initDict = function (selectList,selectId,entityFiled,dufaultName){	
	var html = "";
	if(dufaultName){
		if(!entityFiled || typeof(entityFiled)=="undefined" || entityFiled==0){
			html += '<option value="0" selected>'+dufaultName+'</option>\n';
		}else{
			html += '<option value="0" >'+dufaultName+'</option>\n';
		}
	}
	for(var x in selectList){
		if(entityFiled != 0 && entityFiled == x){
			html += '<option value="'+x+'" selected>'+selectList[x]+'</option>\n'
		}else{
			html += '<option value="'+x+'">'+selectList[x]+'</option>\n'
		}
	}
	document.getElementById(selectId).innerHTML = html;
}*/
/**
 * @param {Element} select 		定义的下拉列表json
 * @param {json} 	dictType 	json名称
 * @param {String} 	defVal 		参数值
 * @param {String} 	nullLabel	默认显示
 */
Gui.initDict = function(select,dictType,defVal,nullLabel,code,text){
	if (!select) return;
	if (!dictType) return;
	var options = {
				list:dictType
			}
	if (nullLabel)
		options.nullLabel = nullLabel;
	if (code)
		options.code = code;
	if (text)
		options.text = text;
	Gui._initSelect(select,options,defVal);	
}

Gui._initSelect = function(select,obj,defVal){
	//alert(typeof (obj.list[0]["code"]))
	//alert(parseInt(obj.list[0]["code"])==obj.list[0]["code"])
	if (typeof select == "string")
		select = $(select);
	if (!select) return;
	select.options.length=0;
	if (obj.onchange)
		select.onchange = obj.onchange;
	if (obj.nullLabel){
		var typeNum = obj.list[0]["code"]
		if(typeof typeNum == "number")//parseInt(value)==value
		//if(parseInt(typeNum) == typeNum)
			select.options[select.options.length]=new Option(obj.nullLabel,'0');
		else
			select.options[select.options.length]=new Option(obj.nullLabel,'');
		
	}
	if (!obj.list) return;
	var code = obj.code||obj.code==0?obj.code:'code';
	var text = obj.text?obj.text:'text';
	for (var i=0;i<obj.list.length;i++){
		var optv = obj.list[i];
		if (!optv) continue;
		var codes = "";
		if (code instanceof Array){
		
			for (var c=0;c<code.length;c++){
				if (codes)
					codes += ",";
				codes += optv[code[c]];
			}	
		}else
			codes = optv[code];
		//-----------------------------修改start--------------
		if(defVal && codes == defVal)
			var opt = new Option(optv[text],codes,null,true);
		else 
			var opt = new Option(optv[text],codes);
		//-----------------------------修改end--------------
		opt.title = optv[text]
		select.options[select.options.length]=opt;
		if (obj.defText==optv[text]||(obj.defCode&&obj.defCode==optv[code])){
			select.selectedIndex = select.options.length-1;
		}
	}
	if (obj.disabled)
	{
		select.disabled = true;
		select.style.background = '#FFFFCC'
	}
}

function a1(node,doNotShowNull){
	var s = ""
	for(var x in node){
		var tmp = (typeof node[x]=="function"?"[function]":node[x]);
		if (tmp && tmp.length>100)
			tmp = tmp.substring(100);
		if (doNotShowNull && tmp == null)
			continue;
		s += x+":\t"+tmp+"\n"
	}
	alert(s)
}

//处理<ul><li><li>...<ul>
function showComplex(dataResponse) {
    var data = eval('(' + dataResponse.responseText + ')');
    var str = '';
    for(var i = 0;i < data.jsonArray.length; i++) { 
        str += '<ul>';
        str += '<li>' + data.jsonArray[i].id + '</li>';
        str += '<li>' + data.jsonArray[i].age + '</li>';
        str += '<li>' + data.jsonArray[i].name + '</li>';
        str += '<li>' + data.jsonArray[i].address + '</li>';
        str += '</ul>';
    }
    document.getElementById("content").innerHTML = str;
}
 
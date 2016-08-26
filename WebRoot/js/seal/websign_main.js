/****************************************************************************************************

方法名：WebSeal						网页盖章或手写
参  数：doaction					操作类型：0 盖章，1 手写签字
		id							印章或手写惟一id，可以为空，为空随机编号
		user						盖章用户名称，可以为空，为空则获取证书用户名
		stype						保护类型：0 绑定固定字符，1 绑定指定编辑区id
		stxt						保护内容：
										当stype为0时，为固定字符串
										当stype为1时，为绑定区域的id
		sid							显示的位置：相对位置的区域div或td的id
		sPostion					显示位置的偏移值，相对绑定区域的左上角0,0，格式为x:y   例如 200:200
		httpaddr					服务器地址，只有服务器版才需要设置，可以为空。

*****************************************************************************************************/
function WebSeal(doaction,id,user,stype,stxt,sid,sPostion,httpaddr) {
	var WebObj = document.getElementById("DWebSignSeal");
	
	if(stype==1){
		WebObj.SetSignData("+LIST:"+stxt+";");
	}else if(stype==0){
		WebObj.SetSignData("+DATA:"+stxt);
	}
	
	WebObj.SetCurrUser(user);
	WebObj.HttpAddress = "http://"+httpaddr+":8089/inc/seal_interface/";
	WebObj.RemoteID = "0100018";
	var str=sPostion.split(":");
	WebObj.SetPosition(str[0],str[1],sid);
	
	if(doaction==0){
		var sealid=document.all.DWebSignSeal.AddSeal("", id);
	}else if(doaction==1){
		var sealid=document.all.DWebSignSeal.HandWrite(0,255,id);
	}
	if (sealid == "") {
		alert("盖章或手写失败！");
	}
	return sealid;
}
/****************************************************************************************************

方法名：GetSealData					获取签章数据
参  数：sid							印章或签名的id，可以为空，为空则获取全部页面的签章数据，有id则获取单个id的签章数据

*****************************************************************************************************/
function GetSealData(sid) {
	var WebObj = document.getElementById("DWebSignSeal");
	
	var v = WebObj.GetStoreDataEx(sid);
	
	if(v.length < 200){
		v="";
	}
	return v;
}
/****************************************************************************************************

方法名：SetSealData					再次加载并显示签章数据
参  数：sealdata					保存的签章数据
		stype						保护类型：0 绑定固定字符，1 绑定指定编辑区id
		stxt						保护内容：
										当stype为0时，为固定字符串
										当stype为1时，为空，无意义
		scheck						校验内容：0 不校验，1校验

*****************************************************************************************************/
function SetSealData(sealdata,stype,stxt,scheck) {
	var WebObj = document.getElementById("DWebSignSeal");
	
	WebObj.SetStoreData(sealdata);
	WebObj.ShowWebSeals();
	strObjectName = WebObj.FindSeal("",0);
	while(strObjectName  != ""){
		if(stype==0){
			WebObj.SetSealSignData(strObjectName,stxt);
		}
		WebObj.SetDocAutoVerify(strObjectName,scheck);
		strObjectName = WebObj.FindSeal(strObjectName,0);
	}
}
/****************************************************************************************************

方法名：SetSealDataEx				只加载签章数据
参  数：sealdata					保存的签章数据

*****************************************************************************************************/
function SetSealDataEx(sealdata) {
	var WebObj = document.getElementById("DWebSignSeal");
	WebObj.SetStoreData(sealdata);
}
/****************************************************************************************************

方法名：MoveSeal					改变印章位置
参  数：id							印章id
		sid							显示的位置：相对位置的区域div或td的id
		sposition					显示位置的偏移值，相对绑定区域的左上角0,0，格式为x:y   例如 200:200

*****************************************************************************************************/
function MoveSeal(id,sid,sposition) {
	var WebObj = document.getElementById("DWebSignSeal");

	var str=sposition.split(":");

	WebObj.MoveSealPosition(id, str[0], str[1], sid);
}
/****************************************************************************************************

方法名：MoveSealEx					改变印章位置
参  数：stype						保护类型：0 绑定固定字符，1 绑定指定编辑区id
		stxt						保护内容：
										当stype为0时，为固定字符串
										当stype为1时，为空，无意义
		scheck						校验内容：0 不校验，1校验
		sposition					显示位置的偏移值，相对绑定区域的左上角0,0，格式为x:y   例如 200:200

*****************************************************************************************************/
function MoveSealEx(stype,stxt,scheck,sposition) {
	var WebObj = document.getElementById("DWebSignSeal");
	//移动位置
	strObjectName = WebObj.FindSeal("",0);
	var str=sposition.split(":");
	while(strObjectName  != ""){
		WebObj.MoveSealPosition(strObjectName,str[0], str[1],strObjectName+"id")
		strObjectName = WebObj.FindSeal(strObjectName,0);
	}
	//显示印章
	WebObj.ShowWebSeals();
	//设置校验数据
	strObjectName = WebObj.FindSeal("",0);
	while(strObjectName  != ""){
		if(stype==0){
			WebObj.SetSealSignData(strObjectName,stxt);
		}
		WebObj.SetDocAutoVerify(strObjectName,scheck);
		strObjectName = WebObj.FindSeal(strObjectName,0);
	}
	//锁定印章
	WebObj.LockSealPosition("");
}
/****************************************************************************************************

方法名：checkData					校验所有印章的有效性
参  数：无

*****************************************************************************************************/
function CheckData() {
	var WebObj = document.getElementById("DWebSignSeal");

	var strObjectName ;
	strObjectName = WebObj.FindSeal("",0);
	while(strObjectName  != ""){
		WebObj.VerifyDoc(strObjectName); 
		strObjectName = WebObj.FindSeal(strObjectName,0);
	}
}
/****************************************************************************************************

方法名：ShowSeal					显示或隐藏印章
参  数：sid							印章id，可以为空，为空则为全部
		setlog						0 隐藏，1 显示

*****************************************************************************************************/
function ShowSeal(sid,setlog) {
	var WebObj = document.getElementById("DWebSignSeal");

	if(sid==""){
		var strObjectName ;
		strObjectName = WebObj.FindSeal("",0);
		while(strObjectName  != ""){
			WebObj.ShowSeal(strObjectName,setlog); 
			strObjectName = WebObj.FindSeal(strObjectName,0);
		}
	}else{
		WebObj.ShowSeal(sid,setlog);
	}
}

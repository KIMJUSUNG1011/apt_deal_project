// 관심 지역 등록 버튼 클릭
document.getElementById('aptLikeButton').addEventListener("click", async function () {

    let sidoName = document.getElementById('sido').value;
    let gugunName = document.getElementById('gugun').value;
    let dongName = document.getElementById('dong').value;
    
	let sendData = {
			method: "post",
			body: JSON.stringify({sidoName, gugunName, dongName}),
			headers: {
				'Content-Type': 'application/json'
			}
	}

    const url = "/home/like";
   
    let data = await fetch(
    		url,
    		sendData
    )

	let result = await data.text();
	alert(result);
	location.href = "/home";
});

// 관심 등록 지역 아파트 조회
document.getElementById('apt_like').addEventListener("click", async function () {
	
    let str = document.getElementById('likeSelect').value;
    let arr = str.split(" ", 3)
    
    let sido = arr[0];
    let gugun = arr[1];
    let dong = arr[2];
   
    const url = "/mvcproject/apt?action=selectAptDetail&sido=" + sido + "&gugun=" + gugun + "&dong=" + dong;
   
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let tbody = document.getElementById('tbody');
    let trs = "";
    let thead = document.getElementById('thead');
    let tr = "<tr scope='col'>"+
	"<td>아파트 명</td>"+
	"<td>도로 명</td>"+
	"<td>건축 연도</td>"+
"</tr>"
    for (let i = 0; i < result.length; i++) {
    	let apartmentName = result[i].apartmentName;
    	let roadName = result[i].roadName;
    	let buildYear = result[i].buildYear;
    	let lng = result[i].lng;
    	let lat = result[i].lat;
    	
    	trs += 
    		"<tr onclick='func(" + lng + "," + lat + ");'><td>" + apartmentName + "</td>\n" +
    		"<td>" + roadName + "</td>\n" +
    		"<td>" + buildYear + "</td></tr>\n";
    }
    thead.innerHTML = tr;
    tbody.innerHTML = trs;
});

// 관심 지역 옵션 세팅
async function setLikeSelect() {
	
    const url = "/home/like";
    
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let likeOpt = document.getElementById('likeSelect');
    
    for (let i = 0; i < result.length; i++) {
    	likeOpt += '<option>' + 
    	result[i].sidoName + " " +  
    	result[i].gugunName + " " + 
    	result[i].dongName + " " + 
    	'</option>';
    }
    
    likeSelect.innerHTML += likeOpt;
}
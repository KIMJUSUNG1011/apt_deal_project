let date = new Date();

window.onload = function () {

    let yearEl = document.querySelector("#year");
    let yearOpt = `<option value="">매매년도선택</option>`;
    let year = date.getFullYear();
    for (let i = year; i > year - 20; i--) {
        yearOpt += `<option value="${i}">${i}년</option>`;
    }

    yearEl.innerHTML = yearOpt;

	sidoSetting();
}

// 시도 선택 세팅
async function sidoSetting() {

    const url = "/home/apt/selectSido";
    
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let sido = document.getElementById('sido');
    let sidoOpt = `<option value="">시도선택</option>`;
    
    for (let i = 0; i < result.length; i++) {
    	sidoOpt += '<option>' + result[i] + '</option>';
    }
    
    sido.innerHTML = sidoOpt;
}

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", async function () {
	
    let sido = document.getElementById('sido');
    const url = "/home/apt/selectGugun/" + sido.value;
   
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let gugun = document.getElementById('gugun');
    let gugunOpt = `<option value="">구군선택</option>`;
    
    for (let i = 0; i < result.length; i++) {
    	gugunOpt += '<option>' + result[i] + '</option>';
    }
    
    gugun.innerHTML = gugunOpt;
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", async function () {

    let sido = document.getElementById('sido');
    let gugun = document.getElementById('gugun');
    const url = "/home/apt/selectDong/" + sido.value + "/" + gugun.value;
   
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let dong = document.getElementById('dong');
    let dongOpt = `<option value="">동선택</option>`;
    
    for (let i = 0; i < result.length; i++) {
    	dongOpt += '<option>' + result[i] + '</option>';
    }
    
    dong.innerHTML = dongOpt;
});

// 년도 선택 시 월 세팅
document.querySelector("#year").addEventListener("change", function () {
    let month = date.getMonth() + 1;
    let monthEl = document.querySelector("#month");
    let monthOpt = `<option value="">매매월선택</option>`;
    let yearSel = document.querySelector("#year")k;
    let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
    for (let i = 1; i < m; i++) {
        monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
    }
    monthEl.innerHTML = monthOpt;
});

// 아파트 실거래가 조회 버튼 클릭
document.getElementById('apt_deal').addEventListener("click", async function () {
	
    let sido = document.getElementById('sido');
    let gugun = document.getElementById('gugun');
    let dong = document.getElementById('dong');
    let year = document.getElementById('year');
    let month = document.getElementById('month');
    
    let url = "";
    
    if (year.value.length != 0 && month.value.length != 0) {
    	url = "/home/apt/selectAptDeal2/" + sido.value + "/" + gugun.value + "/" + dong.value 
    	+ "/" + year.value + "/" + month.value;
    } else {
    	url = "/home/apt/selectAptDeal/" + sido.value + "/" + gugun.value + "/" + dong.value 
    }
   
    let data = await fetch(
    		url,
    		{"method" : "get"}
    )

	let result = await data.json();
    let tbody = document.getElementById('tbody');
    let trs = "";
    let thead = document.getElementById('thead');
    let tr = "<tr>"+
	"<td>아파트명</td>"+
	"<td>매매가</td>"+
	"<td>면적</td>"+
	"<td>매매날짜</td>"+
"</tr>"
    
    for (let i = 0; i < result.length; i++) {
    	let apartmentName = result[i].apartmentName;
    	let dealAmount = result[i].dealAmount;
    	let area = result[i].area;
    	let dealDate = result[i].dealYear + "." + result[i].dealMonth + "." + result[i].dealDay;
    	let lng = result[i].lng;
    	let lat = result[i].lat;
    	
    	trs += 
    		"<tr style=\"cursor:pointer;\" onclick='func(" + lng + "," + lat + ");'><td>" + apartmentName + "</td>\n" +
    		"<td>" + dealAmount + "</td>\n" +
    		"<td>" + area + "</td>\n" +
    		"<td>" + dealDate + "</td></tr>\n";
    }
    thead.innerHTML = tr;
    tbody.innerHTML = trs;
});

// 아파트 상세 조회 버튼 클릭
document.getElementById('apt_detail').addEventListener("click", async function () {
	
    let sido = document.getElementById('sido');
    let gugun = document.getElementById('gugun');
    let dong = document.getElementById('dong');
    
    const url = "/home/apt/selectAptDetail/" + sido.value + "/" + gugun.value + "/" + dong.value;
   
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
    		"<tr style=\"cursor:pointer;\" onclick='func(" + lng + "," + lat + ");'><td>" + apartmentName + "</td>\n" +
    		"<td>" + roadName + "</td>\n" +
    		"<td>" + buildYear + "</td></tr>\n";
    }
    thead.innerHTML = tr;
    tbody.innerHTML = trs;
});

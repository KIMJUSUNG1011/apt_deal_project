// 공지사항 등록
async function add() {

	let title = document.getElementById("title").value;
	let content = document.getElementById("content").value;
	let sendData = {
			method: "post",
			body: JSON.stringify({title, content}),
			headers: {
				'Content-Type': 'application/json'
			}
	}
	
	const url = "/home/notice"

    let data = await fetch(
    		url, 
    		sendData
    )
	
	let result = await data.text();
	alert(result);
	location.href = "/home/notice/goList";
}

// 공지사항 수정
async function update() {
	
	let title = document.getElementById("noticeUpdateTitle").value;
	let content = document.getElementById("noticeUpdateContent").value;
	let noticeIdx = location.href.split("/").pop();

	let sendData = {
			method: "put",
			body: JSON.stringify({title, content}),
			headers: {
				'Content-Type': 'application/json'
			}
	}
	
	const url = "/home/notice/" + noticeIdx;

    let data = await fetch(
    		url, 
    		sendData
    )
	
	let result = await data.text();
    alert(result);
	location.href = "/home/notice/goDetail/" + noticeIdx;
}

// 공지사항 삭제
async function remove() {
	
	let noticeIdx = location.href.split("/").pop();

	let sendData = {
			method: "delete",
			headers: {
				'Content-Type': 'application/json'
			}
	}
	
	const url = "/home/notice/" + noticeIdx;

    let data = await fetch(
    		url, 
    		sendData
    )
	
	let result = await data.text();
    alert(result);
	location.href = "/home/notice/goList";
}

// 공지사항 목록 보기
async function list() {

	let sendData = {
			method: "get"
	}

	const url = "/home/notice/list"

    let data = await fetch(
    		url, 
    		sendData
    )

	let result = await data.json();
    let tbody = document.getElementById('noticeTbody');
    let trs = "";

    for (let i = 0; i < result.length; i++) {
    	let noticeIdx = result[i].noticeIdx;
    	let name = result[i].name;
    	let title = result[i].title;
    	let content = result[i].content;
    	let registerDate = result[i].registerDate;

    	trs += 
    		"<tr onclick=\"location.href='/home/notice/goDetail/" + noticeIdx + "'\" style=\"cursor:pointer;\"'>" +
    		"<th scope=\"row\">" + noticeIdx + "</th>\n" +
    		"<td>" + name + "</td>\n" +
    		"<td>" + title + "</td>\n" +
    		"<td>" + content + "</td>" +
    		"<td>" + registerDate + "</td>" + 
    		"</tr>\n";
    }

    tbody.innerHTML = trs;
}

// 공지사항 상세보기
async function view() {
	
	let noticeIdx = location.href.split("/").pop();
	
	let sendData = {
			method: "get"
	}

	const url = "/home/notice/" + noticeIdx;

    let data = await fetch(
    		url, 
    		sendData
    )
    
	let result = await data.json();
    
    document.getElementById("noticeIdx").placeholder = result.noticeIdx;
    document.getElementById("noticeName").placeholder = result.name;
    document.getElementById("noticeTitle").placeholder = result.title;
    document.getElementById("noticeContent").placeholder = result.content;
    document.getElementById("noticeRegisterDate").placeholder = result.registerDate;
}

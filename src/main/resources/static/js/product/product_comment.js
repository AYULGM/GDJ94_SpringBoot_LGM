console.log("hi hello");

const list = document.getElementById("list");
const commentAdd = document.getElementById("commentAdd");
const contents = document.getElementById("contents");
const close = document.getElementById("close");
const pagelink = document.getElementsByClassName("page-link"); //유사배열이라 foreach안됨

let num = list.getAttribute("data-product-num");


commentList(1); // 읽는순서가중요함 and (1L)이라고 안적는이유가 JS에서는 롱타입이 없어서그럼

list.addEventListener("click", (e) => {
	let t = e.target;
	if(t.classList.contains("page-link")) {
		let p = t.getAttribute("data-pager-num");
		commentList(p);
	}
})

commentAdd.addEventListener("click", () => {
	const param = new URLSearchParams();
	param.append("productNum", num);
	param.append("boardContents", contents.value);
	
	
	fetch("commentAdd", {
		method:"POST",
		body:param
	})
	.then(r=>r.json())
	.then(r=> {
		if(r=='1') { /*성공한다면 '1'말고 1로 해도됨*/
			commentList(1);
		}
	})
	.catch(e => console.log(e))
	.finally(() => {
		close.click();
		contents.value="";
	})
})




function commentList(page) {
	fetch(`./commentList?productNum=${num}&page=${page}`)
	.then(r => r.text())
	.then(r => {
		list.innerHTML=r;
		
		})
	.catch(e => console.log(e))
	;
}

/**
 * 
 */
console.log("hi hello");

const list = document.getElementById("list");
let num = list.getAttribute("data-product-num");


/*외부로 접속한게 아니기때문에 http://localhost/product/detail?productNum=1 이렇게 안해도됨*/
fetch(`./commentList?productNum=${num}`) /* ./은 product 폴더임*/
	.then(r => r.json())
	.then(r => {
		// 내방법
		list.innerHTML = "";
		r.forEach(item => {
			list.innerHTML += `
			  <tr>
			    <td>${item.username}</td>
			    <td>${item.boardContents}</td>
			    <td>${item.boardDate}</td>
			  </tr>
			`;
		});
		// 썜의 방법
		r.forEach(dto => {
			let tr = document.createElement("tr")
			let td = document.createElement("td")
			td.innerText=dto.username;
			tr.append(td);
			td = document.createElement("td");
			td.innerText=dto.boardContents;
			tr.append(td)
			td = document.createElement("td");
			td.innerText=dto.boardDate;
			tr.append(td);
			list.append(tr);
		})
		
		})
	.catch(e => console.log(e))
;
// 텍스트로 넣는방법
// result.innerText = v;
// 태그로 넣는방법
// result.innerHTML="<h3>" + v + "</h3>"
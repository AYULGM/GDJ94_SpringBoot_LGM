/**
 * 
 */

const fileAdd = document.getElementById("fileAdd");
const files = document.getElementById("files");
const del = document.getElementsByClassName("del");
// let idx = 0;
let count=1; // 파일 갯수를 세줄 count변수

files.addEventListener("click", (e) => {
  let d = e.target;
  if (d.classList.contains("del")) {
    d.parentElement.remove();
    count--; // 파일 삭제 시 count 1 감소
    // element.getAttribute("data-del-idx")
    // document.getElementById("id"+d).remove();
  }
})


fileAdd.addEventListener("click", () => {
  

  if(count > 5) {
    alert("최대 5개 까지만 만들 수 있습니다.");
    return;
  }
    
  let div = document.createElement("div");
  let input = document.createElement("input");
  input.type="file";
  input.name="attach" // 파라미터의 이름이 될 name속성
  let button = document.createElement("button");
  button.classList.add("del")
  button.type="button";
  // button.setAttribute("data-del-idx", idx)
  button.innerText="X"

  // append 순서 중요
  // if(del.length < 5){ // 나는 del클래스이름에 조건을 거는걸로 생각했는데 쌤처럼 count 변수 지정하고 if문으로 alert창 띄울 수 있게 하는게 좋아보임.
  div.append(input)
  div.append(button)
  // idx++;
  files.append(div);
// }
count++; // 파일 추가 시 count 1 증가
})


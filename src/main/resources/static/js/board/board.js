/**
 * 
 */

const fileAdd = document.getElementById("fileAdd");
const files = document.getElementById("files");
const del = document.getElementsByClassName("del");
// let idx = 0;
let count=1;

files.addEventListener("click", (e) => {
  let d = e.target;
  if (d.classList.contains("del")) {
    d.parentElement.remove();
    count--;
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
  // if(del.length < 5){
  div.append(input)
  div.append(button)
  // idx++;
  files.append(div);
// }
count++;
})


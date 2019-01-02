let button = document.getElementById("submit")

button.addEventListener('click',function (e) {
    let content = document.getElementById("content").value
    let hour = document.getElementById("hour").value
    let day = document.getElementById("day").value
    let mailWarn = document.getElementById("mailWarn").checked
    axios.post("/todo",{content,hour,day,mailWarn}).then((res)=>{
        if(res.data){
            alert("设置成功");
            document.getElementById("content").value = ""
            document.getElementById("hour").value = ""
            document.getElementById("day").value = ""
            document.getElementById("mailWarn").checked = false;
        }else {
            alert("设置失败");
        }

    })
})
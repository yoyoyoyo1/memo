let button = document.getElementById("submit")

button.addEventListener('click',function (e) {
    let name = document.getElementById("name").value
    let password = document.getElementById("password").value
    axios.post("/user/login",{name,password}).then((res)=>{
        if(res.data){
            var a = document.createElement("A")
            a.setAttribute("href","/index.html")
            a.click()
        }else {
            document.getElementById("message").innerText = "登陆失败"
        }

    })
})
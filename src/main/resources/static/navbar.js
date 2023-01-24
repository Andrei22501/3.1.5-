function navbar() {
    fetch('http://localhost:8080/api/userInfo')
        .then(res => {
        res.json().then(user => {
            document.getElementById('navbarEmail').innerText = user.email;
            document.getElementById('navbarRoles').innerText = user.roles.map((el)=> el.name.toString());
        })
    });
}
navbar()
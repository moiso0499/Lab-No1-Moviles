/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadNavbar() {
    var logueado = JSON.parse(sessionStorage.getItem('logueado'));

    if (logueado !== null) {
        document.querySelector("#navbar_li_user > a").innerHTML = logueado.nombre + " " + logueado.apellido;
        document.getElementById("navbar_li_login").style.display = "none";
        var admin = logueado.admin;
        if (!admin) {
            document.getElementById("navbar_li_planes").style.display = "none";
            document.getElementById("navbar_li_locations").style.display = "none";
            document.getElementById("navbar_li_flights").style.display = "none";
        }
        else{
            document.getElementById("navbar_li_checkFlights").style.display = "none";
        }
    } else {
        document.getElementById("navbar_li_user").style.display = "none";
        document.getElementById("navbar_li_logout").style.display = "none";
        document.getElementById("navbar_li_checkFlights").style.display = "none";
        //Admin
        document.getElementById("navbar_li_planes").style.display = "none";
        document.getElementById("navbar_li_locations").style.display = "none";
        document.getElementById("navbar_li_flights").style.display = "none";
    }
}

function logoutOnClick() {
    sessionStorage.removeItem('logueado');
}

$(loadNavbar);



let form = document.getElementById("form");
let firstname = document.getElementById("firstname");
let lastname = document.getElementById("lastname");
let username = document.getElementById("username");
let email = document.getElementById("email");
let password = document.getElementById("password");
let password2 = document.getElementById("password2");
let address = document.getElementById("address");
let interests =document.getElementById("multiple-select").selectedOptions;
let admin = document.getElementById("admin");
let besucherIn = document.getElementById("besucherIn");
let mitarbeiterIn = document.getElementById("mitarbeiterIn");



form.addEventListener("submit", e => {
    e.preventDefault();

    validateInputs();

});

let setError = (element, message) => {
    let inputControl = element.parentElement;
    let errorDisplay = inputControl.querySelector(".error");

    errorDisplay.innerText = message;
    inputControl.classList.add("error");
    inputControl.classList.remove("success")
}

let setSuccess = element => {
    let inputControl = element.parentElement;
    let errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add("succsess");
    inputControl.classList.remove("error");
};

let isValidUser = username => {
    let regex = new RegExp("^[a-zA-Z_]{5,12}$");
    return regex.test((username));
}

let isValidEmail = email => {
    let regex = new RegExp("^[\\w0-9-]+(?:[._-][\\w0-9]+)*@[\\w0-9]{3,63}(.[a-z]{3,63})+$");
    return regex.test((email));
}

let isValidPassword = password => {
    let regex = new RegExp("^[a-zA-Z]([_](?![_])|[a-zA-Z0-9]){5,10}$")
    return regex.test((password));
}


let validateInputs = () => {
    let firstnameValue = firstname.value;
    let lastnameValue = lastname.value;
    let usernameValue = username.value;
    let emailValue = email.value;
    let passwordValue = password.value;
    let password2Value = password2.value;
    let addressValue = address.value;


    if (firstnameValue === '') {
        setError(firstname, "Bitte gib deinen Vornamen ein!");
    } else {
        setSuccess(firstname);
    }

    if (lastnameValue === '') {
        setError(lastname, "Bitte gib deinen Nachnamen ein!");
    } else {
        setSuccess(lastname);
    }
    if (addressValue === '') {
        setError(address, "Bitte gib deine Addresse ein!");
    } else {
        setSuccess(address);
    }

    if (usernameValue === '') {
        setError(username, "Bitte gib einen gewünschten Benutzernamen ein!");
    } else if (!isValidUser(usernameValue)) {
        setError(username, "Benuztername darf nur Buchstaben und Unterstriche enthalten!")
    } else {
        setSuccess(username);
    }

    if (emailValue === '') {
        setError(email, "Bitte gib eine gültige E-Mailadresse ein!");
    } else if (!isValidEmail(emailValue)) {
        setError(email, "E-Mail ist nicht valide!");
    } else {
        setSuccess(email);
    }

    if (passwordValue === '') {
        setError(password, "Such dir ein Passwort aus!");
    } else if (!isValidPassword(passwordValue)) {
        setError(password, "Passwortlänge muss 6-11 Zeichen lang sein und darf nur Buchstaben, Ziffern und _ enthalten!")
    } else {
        setSuccess(password);
    }

    if (password2Value === '') {
        setError(password2, "Bitte bestätige dein Passwort!");
    } else if (password2Value !== passwordValue) {
        setError(password2, "Passwörter stimmen nicht überein!");
    } else {
        setSuccess(password2);
    }


    if (interests.length <= 0) {
        alert("Wähle min. 1 Interesse aus!");
        return false;
    }

    if(!(admin.checked||mitarbeiterIn.checked||besucherIn.checked)){
        alert("Wähle eine Kontoart aus!");
        return false;
    }

};


//popup
function openMe() {
   var popwin = window.open("anne.png", "_blank", " width = 100%, scrollbars=no,resizable=no");
   popwin.resizeBy(426,600);
   popwin.focus();
   popwin.document.write('<button onclick="window.close()">Schliessen</button>', '<img id="annefrank" src= "anne.png" width="416" height="600"/>');

}




function openBook(){
    var img = new Image;
    img.src = "anne.png";

    var realWidth = img.width;
    var realHeight = img.height;

    var popwindow = "width=" + realWidth + ",height=" + realHeight;

    var myWindow = window.open("", "_blank", popwindow);
    myWindow.document.write('<img src="anne.png"/>', '<button onclick="self.close()"/>');
    //myWindow.document.write("<img src='anne.png' onclick='self.close()'/>");
    //myWindow.document.write("<a href='' onclick='self.close()'>Schliessen</a>");
}









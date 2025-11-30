function updateDetails() {
    var select = document.getElementById("pic");
    if (select.selectedIndex === 0) {
        // Clear fields if "-- Select --" is chosen
        document.getElementById("alamat-display").value = "";
        document.getElementById("password-display").value = "";
        return;
    }

    var option = select.options[select.selectedIndex];
    
    // Get the data from the option
    var alamat = option.getAttribute("data-alamat");
    var password = option.getAttribute("data-password");
    
    // Set the text box values. The '|| ""' 
    // handles any 'null' values perfectly.
    document.getElementById("alamat-display").value = alamat || "";
    document.getElementById("password-display").value = password || "";
}
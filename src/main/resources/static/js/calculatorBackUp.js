function calcPrice(size, sheets, colors){ //TODO add arguments for sheets, colors, size
    let sheet50x35 = {
        size: "50x35",
        quantity:    [25, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 800],
        1: [3.28, 1.96, 1.28, 1.22, 1.12, 1.07, 1.02, 0.96, 0.92, 0.89, 0.85, 0.85],
        2: [5.94, 3.57, 2.32, 1.91, 1.77, 1.69, 1.62, 1.55, 1.51, 1.44, 1.39, 1.39],
        3: [8.60, 4.98, 3.23, 2.56, 2.36, 2.27, 2.19, 2.11, 2.05, 1.99, 1.91, 1.91],
        4: [11.24, 6.53, 4.24, 3.21, 2.97, 2.88, 2.79, 2.73, 2.65, 2.57, 2.48, 2.48],
        5: [14.17, 8.23, 5.34, 3.86, 3.51, 3.41, 3.30, 3.22, 3.13, 3.05, 2.95, 2.95],
        6: [16.19, 9.56, 6.21, 4.52, 4.11, 3.99, 3.87, 3.79, 3.68, 3.61, 3.50, 3.50],
    };
    let sheet40x30 = {
        size: "40x30",
        quantity:    [25, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 800],
        1: [2.86, 1.87, 1.22, 1.16, 1.07, 1.02, 0.97, 0.91, 0.88, 0.85, 0.81, 0.81],
        2: [5.17, 3.25, 2.11, 1.74, 1.61, 1.54, 1.47, 1.41, 1.37, 1.31, 1.26, 1.26],
        3: [7.48, 4.51, 2.94, 2.33, 2.15, 2.07, 1.99, 1.92, 1.87, 1.81, 1.74, 1.74],
        4: [9.78, 5.94, 3.86, 2.92, 2.70, 2.62, 2.54, 2.48, 2.41, 2.34, 2.26, 2.26],
        5: [12.32, 7.48, 4.86, 3.51, 3.19, 3.10, 3.00, 2.93, 2.85, 2.78, 2.69, 2.69],
        6: [14.08, 8.69, 5.65, 4.11, 3.74, 3.63, 3.52, 3.45, 3.35, 3.28, 3.19, 3.19],
    };
    let sheet33x35 = {
        size: "33x35", //5% over the price of 40x30cm
        quantity:    [25, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 800],
        1: [2.72, 1.78, 1.16, 1.10, 1.02, 0.97, 0.92, 0.86, 0.84, 0.81, 0.77, 0.77],
        2: [4.91, 3.09, 2.00, 1.65, 1.53, 1.46, 1.40, 1.34, 1.30, 1.24, 1.20, 1.20],
        3: [7.11, 4.28, 2.79, 2.21, 2.04, 1.97, 1.89, 1.82, 1.78, 1.72, 1.65, 1.65],
        4: [9.29, 5.64, 3.67, 2.77, 2.57, 2.49, 2.41, 2.36, 2.29, 2.22, 2.15, 2.15],
        5: [11.7, 7.11, 4.62, 3.33, 3.03, 2.95, 2.85, 2.78, 2.71, 2.64, 2.56, 2.56],
        6: [13.38, 8.26, 5.37, 3.90, 3.55, 3.45, 3.34, 3.28, 3.18, 3.12, 3.03, 3.03],
    };
    let sheet25x35 = {
        size: "25x35",
        quantity:    [25, 50, 100, 150, 200, 300],
        1: [2.60, 1.70, 1.11, 1.05, 0.97, 0.91],
        2: [4.70, 2.95, 1.92, 1.58, 1.46, 1.46],
        3: [6.80, 4.10, 2.67, 2.12, 1.95, 1.95],
        4: [8.90, 5.40, 3.51, 2.65, 2.45, 2.45],
        5: [11.20, 6.80, 4.42, 3.19, 2.90, 2.90],
        6: [12.80, 7.90, 5.14, 3.75, 3.40, 3.40],
    };

    let qtyInput = sheets;
    let colorsInput = colors;
    const addtionalColorPrice = 0.50;
    const maxColorsInPriceList = 6;
    let qtyRange = 0;
    let price = 0;
    if (colors > 6){
        colorsInput = 6;
    }


    if(qtyInput >= 800 && size === "50x35"){
        alert("Bitte kontaktieren Sie uns für ein individuelles Angebot.");
        return 0;
    }else if(qtyInput > 300 && size === "25x35"){
        alert("Wir empfehlen, ein größeres Blatt zu wählen. Bitte kontaktieren Sie uns für ein individuelles Angebot.");
        return 0;
    }else if(qtyInput > 800 && size === "40x30"){
        alert("Bitte kontaktieren Sie uns für ein individuelles Angebot.");
        return 0;
    }else if(qtyInput > 500 && size === "33x35"){
        alert("Bitte kontaktieren Sie uns für ein individuelles Angebot.");
        return 0;
    }

    if(size === "50x35") { //TODO make a checking with the argument of sheet size
        for (let i = 0; i <= sheet50x35["quantity"].length; ++i) {
            if (qtyInput >= sheet50x35["quantity"][i] && qtyInput < sheet50x35["quantity"][i + 1]) {
                qtyRange = sheet50x35["quantity"][i];
                let colIndex = sheet50x35["quantity"].indexOf(qtyRange);
                price = sheet50x35[colorsInput][colIndex];
                break;
            }else if (qtyInput < sheet50x35["quantity"][0]) {
                alert("Mindestbestellmenge - 25 Blatt");
                return -1;
            }
        }
    }else if(size === "40x30"){
        for (let i = 0; i <= sheet40x30["quantity"].length; ++i) {
            if (qtyInput >= sheet40x30["quantity"][i] && qtyInput < sheet40x30["quantity"][i + 1]) {
                qtyRange = sheet40x30["quantity"][i];
                let colIndex = sheet40x30["quantity"].indexOf(qtyRange);
                price = Number(sheet40x30[colorsInput][colIndex]);
                break;
            }else if (qtyInput < sheet40x30["quantity"][0]) {
                alert("Mindestbestellmenge - 25 Blatt");
                return -1;
            }
        }
    }else if(size === "33x35"){
        for (let i = 0; i <= sheet33x35["quantity"].length; ++i) {
            if (qtyInput >= sheet33x35["quantity"][i] && qtyInput < sheet33x35["quantity"][i + 1]) {
                qtyRange = sheet33x35["quantity"][i];
                let colIndex = sheet33x35["quantity"].indexOf(qtyRange);
                price = sheet33x35[colorsInput][colIndex];
                break;
            } else if (qtyInput < sheet33x35["quantity"][0]) {
                alert("Mindestbestellmenge - 25 Blatt");
                return -1;
            }
        }
    }else if(size === "25x35"){
        for (let i = 0; i <= sheet25x35["quantity"].length; ++i) {
            if (qtyInput >= sheet25x35["quantity"][i] && qtyInput < sheet25x35["quantity"][i + 1]) {
                qtyRange = sheet25x35["quantity"][i];
                let colIndex = sheet25x35["quantity"].indexOf(qtyRange);
                price = sheet25x35[colorsInput][colIndex];
                break;
            }else if (qtyInput < sheet25x35["quantity"][0]) {
                alert("Mindestbestellmenge - 25 Blatt");
                return -1;
            }
        }
    }

    if (colors > maxColorsInPriceList){
        let additionalColors = colors - maxColorsInPriceList;
        let additionalCost = additionalColors * addtionalColorPrice;
        price += additionalCost;
    }
    //alert(colorsInput);
    //alert(qtyRange +" and "+ price);
    return price;
}

// function totalPrice(sheetPrice) {
//     let price = sheetPrice;
//     let transparentFilm = 0.20 * price;
//     let sublimatingMaterials = 0.10 * price;
//     let metallicColors = 0.15 * price;
//     let heatSensitive = 0.10 * price;
//
//     price = price + transparentFilm + sublimatingMaterials + metallicColors + heatSensitive;
//     return price;
// }
function invisibleColors() {
    let type = document.getElementsByName("type");
    let singleSpotLB = document.getElementById("singleSpotLB");
    let multiSpotLB = document.getElementById("multiSpotLB");
    let photoSilkLB = document.getElementById("photoSilkLB");

    if (type[0].checked) {
        document.getElementById('colorDiv').style.display = "none";
        singleSpotLB.style.display = "block";
        multiSpotLB.style.display = "none";
        photoSilkLB.style.display = "none";
    }else if (type[1].checked) {
        document.getElementById('colorDiv').style.display = "block";
        singleSpotLB.style.display = "none";
        multiSpotLB.style.display = "block";
        photoSilkLB.style.display = "none";
    }else if (type[2].checked) {
        document.getElementById('colorDiv').style.display = "none";
        singleSpotLB.style.display = "none";
        multiSpotLB.style.display = "none";
        photoSilkLB.style.display = "block";
    }
}
//Remove Reload Resubmission Message
if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

// function shippingCost(sheets, size) {
//     let shippingCost = Number(0);
//     let weightSheet = weightCalc(this.sheets, this.size);
//     if (size === "25x35" && weightSheet > 1){
//         alert(weightSheet);
//     }
//
//     function weightCalc(sheets, size) {
//         let weight = Number(0);
//         if(size === "25x35"){
//             weight = sheets * Number(0.014);
//         }else if(size === "33x35"){
//             weight = sheets * Number(0.018);
//         }else if(size === "40x30"){
//             weight = sheets * Number(0.022);
//         }else if(size === "50x35"){
//             weight = sheets * Number(0.025);
//         }
//         return Number(weight).toFixed(2);
//     }
// }


function getType() {
    //Inputs
    let type = document.getElementsByName("type");
    let numColors = document.getElementById("numColors");
    let actualColorNumber = numColors.value;
    let sheetSize = document.getElementById("sheetSize").value;
    let numSheets = document.getElementById("numSheets").value;
    let darkGarment = document.getElementsByName("darkGarment");
    //Additional options
    let transFilm = document.getElementById("transFilm");
    const transFilmRate = Number(0.20);
    let blocker = document.getElementById("blocker");
    const blockerRate = Number(0.10);
    let metallic = document.getElementById("metallic");
    const metallicRate = Number(0.15);
    let powder = document.getElementById("powder");
    const powderRate = Number(0.10);
    let totalAdditionalOptions = Number(0);
    let finalSheetCost = Number(0);
    //Table
    let addOptPrice = document.getElementById("addOptPrice");
    let sheetPriceColumn = document.getElementById("sheetPriceColumn");
    let totalPriceColumn = document.getElementById("total");
    if (sheetSize.value !== '' && darkGarment !== undefined){
        if (type[0].checked && darkGarment[0].checked){
            numColors.value = Number(2);
            actualColorNumber = Number(numColors.value);
        }
        if(type[0].checked && darkGarment[1].checked){
            numColors.value = Number(1);
            actualColorNumber = numColors.value;
        }
        if(type[1].checked && darkGarment[0].checked){
            numColors.value = Number(numColors.value);
            actualColorNumber = Number(numColors.value)+1;
            darkGarment.disabled;
        }
        if(type[1].checked && darkGarment[1].checked){
            //numColors.value = Number(numColors.value);
            if (darkGarment[1].checked){
                actualColorNumber = Number(numColors.value);
                darkGarment.disabled;
            }
        }
        if(type[2].checked && darkGarment[0].checked){
            //numColors.value = Number(numColors.value);
            if (darkGarment[0].checked){
                actualColorNumber = Number(5);
                darkGarment.disabled;
            }
        }
        if(type[2].checked && darkGarment[1].checked){
            //numColors.value = Number(numColors.value);
            if (darkGarment[1].checked){
                actualColorNumber = Number(4);
                darkGarment.disabled;
            }
        }
        let sheetPrice = Number(calcPrice(sheetSize, numSheets, actualColorNumber)).toFixed(2);
        //alert(sheetPrice);
        //let check = getAdditionalOptions(sheetPrice);

        if (transFilm.checked){
            transFilm.value = (Number(sheetPrice) * transFilmRate).toFixed(2);
        }else{
            transFilm.value = Number(0);
        }
        if (blocker.checked){
            blocker.value = (Number(sheetPrice) * Number(blockerRate)).toFixed(2);
        }else{
            blocker.value = Number(0);
        }
        if (metallic.checked){
            metallic.value = (Number(sheetPrice) * Number(metallicRate)).toFixed(2);
            document.getElementById("coldpeel").style.display = "none";
            document.getElementById("hotpeel").style.display = "block";
        }else{
            metallic.value = Number(0);
            document.getElementById("coldpeel").style.display = "block";
            document.getElementById("hotpeel").style.display = "none";
        }
        if (powder.checked){
            powder.value = (Number(sheetPrice) * Number(powderRate)).toFixed(2);
            document.getElementById("degree180").style.display = "none";
            document.getElementById("degree150").style.display = "block";
            document.getElementById("sec8").style.display = "none";
            document.getElementById("sec10").style.display = "block";
        }else{
            powder.value = Number(0);
            document.getElementById("degree180").style.display = "block";
            document.getElementById("degree150").style.display = "none";
            document.getElementById("sec8").style.display = "block";
            document.getElementById("sec10").style.display = "none";
        }
        // shippingCost(numSheets, sheetSize);
        totalAdditionalOptions = Number(transFilm.value) + Number(blocker.value) + Number(metallic.value) + Number(powder.value);
        finalSheetCost = (Number(sheetPrice) + Number(totalAdditionalOptions)).toFixed(2);
        totalPriceColumn.textContent = Number(finalSheetCost * Number(numSheets)).toFixed(2) + " €";
        addOptPrice.textContent = numSheets + " sheets";
        sheetPriceColumn.textContent = finalSheetCost + " €/sheet";
        //alert(numColors.value + " and sheet: " + sheetSize.value);
    }else {
        alert("Please choose a sheet size");
    }
}

function printPage(divName) {
    let printableTitle = document.getElementById("printableTitle");
    printableTitle.style.display = "block";
    let printContent = document.getElementById(divName).innerHTML;
    printableTitle.style.display = "none";
    let originalContent = document.body.innerHTML;
    document.body.innerHTML = printContent;
    window.print();
    document.body.innerHTML = originalContent;


}



function successfulSubmittion(email){
    console.log("please confirm you email address - http://localhost:8080/confirm?email=" + email);
}

function userFormSubmit(){
    let emailInput = $("#inputEmail").val();
    let originInput = $("#inputOrigin").val();
    let destinationInput = $("#inputDestination").val();
    let frequencyInput = $("#frequencySelect").val();
    let formData = {
        email: emailInput,
        origin: originInput,
        destination: destinationInput,
        frequency: {
            frequencyId: frequencyInput
        }
    };
    let url = "/api/user/create-new";
    let jsonObject = JSON.stringify(formData);
    $j.ajax({
        url: url,
        type: "POST",
        data: jsonObject,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (data, status, xhttp) {
            if (data === 'Failed: could not execute statement') {
                alert('Failed: Either the email exists or is invalid!')
            }
            if (data === "Success"){
                successfulSubmittion(emailInput);
            }
        }
    });
}

function populateFrequencySelection(frequencies){
    let list = $("#frequencySelect");
    frequencies.forEach(function (frequency) {
       let option = $("<option>");
       option.attr("value", frequency.frequencyId);
       option.text(frequency.frequencyPeriod);
       list.append(option);
    });
}

function callFrequencyListForPopulation(){
    let url = "/api/frequency/all";
    $j.get(url, function (data) {
       populateFrequencySelection(data);
    });
}

$(document).ready(function () {
    callFrequencyListForPopulation();
   $("#userSubmitionForm").submit(function (e) {
      e.preventDefault();
      userFormSubmit();
   });
});
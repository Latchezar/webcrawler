function successfulSubmittion(email){
    window.location.href = "/confirm?email=" + email;
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
            console.log(data);
            console.log(status);
            if (data === "Success"){
                successfulSubmittion(emailInput);
            }
        }
    });
}

$(document).ready(function () {
   $("#userSubmitionForm").submit(function (e) {
      e.preventDefault();
      userFormSubmit();
   });
});
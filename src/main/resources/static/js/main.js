"use strict";

$(function () {

    function getImage() {
        const icon = $("#icon").val();
        const weatherIcon = '<span><img src="http://openweathermap.org/img/wn/' + icon + '@4x.png"' + ' alt="img"' + ' alt="control" ></span>'
        $("#wxIcon").html(weatherIcon);
    }
    getImage();

    function getStreetView() {
        const streetAdd = $("#streetAdd").text();
        const zip = $("#zip").text();
        const address = streetAdd + ', ' + zip;

        console.log(streetAdd);
        console.log(zip);
        console.log(address);

        geocode(address, mapBoxKey).then(function (results) {
            console.log(results);
            const convertedResult = results.reverse().toString();
            console.log(convertedResult);
            let imageRequest = ''
            imageRequest = '<img src="https://maps.googleapis.com/maps/api/streetview?location=' + convertedResult + '&size=332x200&key=' + googAPIKey + '" alt="test" >';
            $('#streetView').html(imageRequest);
        });
    }
    getStreetView();


    let panorama;

    function initialize() {
        panorama = new google.maps.StreetViewPanorama(
            document.getElementById("street-view"),
            {
                position: { lat: 37.86926, lng: -122.254811 },
                pov: { heading: 165, pitch: 0 },
                zoom: 1,
            }
        );
    }

    window.initialize = initialize;



    // imageRequest = '<img src="https://maps.googleapis.com/maps/api/streetview?location=' + convertedResult + '&size=256x256&key=' + googAPIKey + '" alt="test" >';


    // width="100" height="100"
    // "http://openweathermap.org/img/w/'

    // function tempWarning() {
    //     const temp = $("#temp").val();
    //     console.log(parseFloat(temp));
    //     console.log("test");
    //     if (temp > 90.00) {
    //         var warmWarning = '<div class="card prop-card" id="maintCard">' +
    //             '<h4 class="card-header">Hot Weather Related</h4>' +
    //             '<ul class="list-group list-group-flush">' +
    //             '<li class="list-group-item">If property is located on exansive soil, consider watering foundation.</li>' +
    //             '<li class="list-group-item">If property has elderly occupants, ensure they have adequate cooling/ventilation.</li>' +
    //             '<li class="list-group-item">Keep AC between 75-78 degrees to prevent coil from freezing.</li>' +
    //             '<li class="list-group-item">Watch pets for signs of heat injury.</li>' +
    //             '</ul>' +
    //             '</div>'
    //         $("#wxTempWarning").html(warmWarning);
    //     }
    //     if (temp < 32.00){
    //         var coldWarning =  '<div class="card prop-card" id="maintCard">' +
    //             '<h4 class="card-header">Cold Weather Related</h4>' +
    //             '<ul class="list-group list-group-flush">' +
    //             '<li class="list-group-item">Cover exposed exterior pipes and bibs.</li>' +
    //             '<li class="list-group-item">If property is vacant, keep thermostat to a minimum of 50–55 degrees.</li>' +
    //             '<li class="list-group-item">Run the taps periodically.</li>' +
    //             '<li class="list-group-item">Cover plants.</li>' +
    //             '<li class="list-group-item">Bring in pets.</li>' +
    //             '</ul>' +
    //             '</div>'
    //         $("#wxTempWarning").html(coldWarning);
    //     }
    // }
    // tempWarning();

});

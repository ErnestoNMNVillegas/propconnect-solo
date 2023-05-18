"use strict";


// function initPano() {
//     console.log("test");
//     var panorama = new google.maps.StreetViewPanorama(
//         document.getElementById('street-view'),
//         {
//             position: { lat: 42.345573, lng: -71.098326 },
//             addressControlOptions: {
//                 position: google.maps.ControlPosition.BOTTOM_CENTER,
//             },
//             linksControl: false,
//             panControl: false,
//             enableCloseButton: false,
//         }
//     );
// }

// window.initPano = initPano;


// function initialize() {
//
//     var geocoder = new google.maps.Geocoder();
//     const streetAdd = $("#streetAdd").text();
//     const zip = $("#zip").text();
//     const address = streetAdd + ', ' + zip;
//     // var address = "2 Simei Street 3, Singapore, Singapore 529889";
//
//     geocoder.geocode({'address': address}, function (results, status) {
//         if (status === google.maps.GeocoderStatus.OK) {
//             var latitude = results[0].geometry.location.lat();
//             var longitude = results[0].geometry.location.lng();
//
//             console.log(latitude + " " + longitude);
//
//             var svService = new google.maps.StreetViewService();
//             var panoRequest = {
//                 location: results[0].geometry.location,
//                 preference: google.maps.StreetViewPreference.NEAREST,
//                 radius: 50,
//                 source: google.maps.StreetViewSource.OUTDOOR
//             };
//
//             var findPanorama = function(radius) {
//                 panoRequest.radius = radius;
//                 svService.getPanorama(panoRequest, function(panoData, status){
//                     if (status === google.maps.StreetViewStatus.OK) {
//                         var panorama = new google.maps.StreetViewPanorama(
//                             document.getElementById('street-view'),
//                             {
//                                 pano: panoData.location.pano,
//                             });
//                     } else {
//                         //Handle other statuses here
//                         if (radius > 200) {
//                             alert("Street View is not available");
//                         } else {
//                             findPanorama(radius + 5);
//                         }
//                     }
//                 });
//             };
//
//             findPanorama(50);
//         }
//     });
// }



//FH Koordinaten
var map = L.map('mapid').setView([ 47.407, 9.743], 15);
// Kartenlayer aufruf
L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw',
    {
        maxZoom : 18,
        attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, '
           ,
        id : 'mapbox/streets-v11',
        tileSize : 512,
        zoomOffset : -1
    }).addTo(map);

//popUp erzuegen
var popup = L.popup();

//beim Klicken auf die Karte werden generell die Koordinaten angezeigt
function onMapClick(e) {
    popup.setLatLng(e.latlng).setContent(
        "Die Koordinaten sind: " + e.latlng.toString()).openOn(
        map);
}
map.on('click', onMapClick);

let markers = [];

// api call indem man jQuery verwendet, parsen & regex - https://rss2json.com/docs
$.ajax({
    url: 'https://api.rss2json.com/v1/api.json',
    method: 'GET',
    dataType: 'json',
    data: {
        rss_url: 'https://www.festivalticker.de/rss-festivalfeed/festivals-at.xml',
        //api_key: '97mgcx6tsg1wzqddwxu7qlgtbrhyn8c0aizj0pry',
        count: 10
    }

}).done(function (response) {
    if(response.status != 'ok'){ throw response.message; }
    const regularexpresion =
        /Ort: \d+ ([\wüÜäÄöÖß -]*)<br>Land: ([\wßäöüÄÖÜ -]*)/gm;
    console.log('====== ' + response.feed.title + ' ======');

    //zu html hinzufügen
    for(var i in response.items){
        var item = response.items[i];
        var event = $('<div>');
        event.html(`<h3>${item.title}</h3><p>${item.description}</p></div>`)

        console.log(item.title);


        matches = regularexpresion.exec(item.description);
        console.log(matches);
        event.attr('id', i);

        if(matches) {

            var alocation = `${matches[1]} ${matches[2]}`;
            event.attr("location", alocation).attr("title", item.title).attr("clicked", false);
            event.on('click', function() {
                if(this.getAttribute("clicked") === "false") {
                    this.setAttribute("clicked", true);
                    console.log(this.getAttribute("id"));
                    newMarker(this.getAttribute("title"), this.getAttribute("location"), this.getAttribute("id"));
                } else {
                    //marker wird gesetzt "fliegt" dorthin
                    map.flyTo(markers[this.getAttribute("id")], 12);
                }
            });

        }
        $('#feed').append(event);
    }
});

//neue marker - koordinaten werden zurückgeliefert
function newMarker(title, eventLocation, id) {
    $.get(`https://nominatim.openstreetmap.org/search?q=${eventLocation}&format=json&limit=1&adressdetails=1`, function(response2) {
        var latitude = response2[0].lat;
        var longitude = response2[0].lon;

        var marker = L.marker([latitude, longitude]).addTo(map);
        marker.bindPopup(`${title}`).openPopup();

        markers[id] = ([latitude, longitude]);
    });
}
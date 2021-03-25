const listingList = document.getElementById('listingList')
const searchBar = document.getElementById('searchInput')

const allListings = [];

fetch("http://localhost:8080/stay/api").then(response => response.json()).then(data => {
    for (let listing of data) {
        allListings.push(listing);
    }
})

console.log(allListings);

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredListings = allListings.filter(l => {
        return l.city.toLowerCase().includes(searchingCharacters);
    });

    listings(filteredListings);
})


const listings = (l) => {
    listingList.innerHTML = l
        .map((a) => {
            return `<div class="card m-2">
                        <h5 class="card-header text-center" >${a.listingTitle}</h5>
                        <div class="card-body">
                            <img class="float-left mr-3" height="100" src="${a.firstPicture}">
                            <h5 class="card-title">${a.country}, ${a.city}</h5>
                            <a class="card-text">Type - ${a.stayType}</a><br>
                            <a class="card-text">Available Rooms: ${a.availabilityLeft}</a><br>
                            <a class="card-text">Price per night: ${a.pricePerNight}</a>
                                                   
                            <hr>

                            <div class="text-center">
                                <h6 class="font-weight-bold">About the place</h6>
                                <span class="bg-gray-300">${a.description}</span>
                            </div>
                            <hr>
                            <a href="/stay/view/${a.id}" class="btn btn-outline-info btn-block">View</a>
                        </div>
                    </div> `
        })
        .join('');

}
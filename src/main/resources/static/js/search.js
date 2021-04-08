const listingList = document.getElementById('listingList')
const searchBar = document.getElementById('searchInput')
const searchByMinPrice = document.getElementById('searchInputByMinPrice')
const searchByMaxPrice = document.getElementById('searchInputByMaxPrice')
const searchHotels = document.getElementById('HOTEL')
const searchGuestHouse = document.getElementById('GUEST_HOUSE')
const searchRoom = document.getElementById('ROOM')
const searchApartment = document.getElementById('APARTMENT')

const allListings = [];

fetch("http://localhost:8080/stay/api").then(response => response.json()).then(data => {
    for (let listing of data) {
        allListings.push(listing);
    }
})

console.log(allListings);

searchGuestHouse.addEventListener('click', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.stayType === 'GUEST_HOUSE';
    });

    listings(filteredListings);
})

searchRoom.addEventListener('click', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.stayType === 'ROOM';
    });

    listings(filteredListings);
})

searchApartment.addEventListener('click', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.stayType === 'APARTMENT';
    });

    listings(filteredListings);
})

searchHotels.addEventListener('click', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.stayType === 'HOTEL';
    });

    listings(filteredListings);
})

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredListings = allListings.filter(l => {
        return l.city.toLowerCase().includes(searchingCharacters);
    });

    listings(filteredListings);
})

searchByMinPrice.addEventListener('keyup', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.pricePerNight >= searchByMinPrice.value;
    });

    listings(filteredListings);
})
searchByMaxPrice.addEventListener('keyup', (e) => {
    let filteredListings = allListings.filter(l => {
        return l.pricePerNight <= searchByMaxPrice.value;
    });

    listings(filteredListings);
})


const listings = (l) => {
    listingList.innerHTML = l
        .map((a) => {
            return `<div class="card m-2">
                        <h5 class="card-header text-center" >${a.listingTitle}</h5>
                        <div class="card-body">
                            <img class="float-left mr-3" height="125" width="150" src="${a.firstPicture}">
                            <h5 class="card-title">${a.country}, ${a.city}</h5>
                            <a class="card-text">Type - ${a.stayType}</a><br>
                            <a class="card-text">Available Rooms: ${a.availabilityLeft}</a><br>
                            <a class="card-text">Price per night: $${a.pricePerNight}</a>
                                                   
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
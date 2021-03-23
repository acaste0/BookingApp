const listingList = document.getElementById('listingList')
const searchBar = document.getElementById('searchInput')

const allListings = [];

fetch("http://localhost:8080/stay/api").
then(response => response.json()).
then(data => {
    for (let listing of data) {
        allListings.push(listing);
    }
})

console.log(allListings);

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredListings = allListings.filter(l => {
        return l.listingTitle.toLowerCase().includes(searchingCharacters);
    });

    listings(filteredListings);
})


const listings = (l) => {
    listingList.innerText = '';
    listingList.innerHTML = l
        .map((a) => {
            return ` <div class="card m-2">
                        <h5 class="card-header text-center" >${a.listingTitle}</h5>
                        <div class="card-body">
                            <img class="float-left mr-3" height="100" th:src="*{a.pictures.get(0).pictureUrl}">
                            <h5 class="card-title" th:text="'' + *{a.country} + ', ' + *{a.city}"></h5>
                            <a class="card-text" th:text="'Type - '+  *{a.stayType.getName()}"></a><br>
                            <a class="card-text" th:text="'Available Rooms: '+  *{a.availabilityLeft}"></a><br>
                            <a class="card-text" th:text="'Price per night: '+  *{a.pricePerNight}"></a>
                          
                            <hr>
                            <div class="text-center">
                                <h6 class="font-weight-bold">Stay Properties</h6>
                                <div class="row justify-content-center">
                                    <ul class="list-group col-auto">
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasWifi} ? '✔ Wifi' : '✖ Wifi'"></li>
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasTV} ? '✔ TV' : '✖ TV'"></li>
                                    </ul>
                                    <ul class="list-group col-auto">
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasBathroom} ? '✔ Bathroom' : '✖ Bathroom'"></li>
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasBedroom} ? '✔ Bedroom' : '✖ Bedroom'"></li>
                                    </ul>
                                    <ul class="list-group col-auto">
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasFreeBreakfast} ? '✔ Free Breakfast' : '✖ Free Breakfast'"></li>
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasRestaurant} ? '✔ Restaurant' : '✖ Restaurant'"></li>
                                    </ul>
                                    <ul class="list-group col-auto">
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasParking} ? '✔ Parking' : '✖ Parking'"></li>
                                        <li class="list-group-item"
                                            th:text="*{a.stayProperties.hasCityView} ? '✔ City View' : '✖ City View'"></li>
                                    </ul>
                                </div>
                            </div>
                            <hr>

                            <div class="text-center">
                                <h6 class="font-weight-bold">About the place</h6>
                                <span class="bg-gray-300" th:text="*{a.description}"></span>
                            </div>
                            <hr>
                            <a th:href="@{'/stay/view/' + *{a.id}+''}" class="btn btn-outline-info btn-block">View</a>
                        </div>
                    </div>`
        })
        .join('');

}
$(document).ready(
    function () {

        // GET REQUEST
        $("#getAllListingLogs").click(function (event) {
            event.preventDefault();
            ajaxGet();
        });

        // DO GET
        function ajaxGet() {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/logs/listings",
                success: function (result) {
                    if (result.status == "success") {
                        if (result.length == 0) {
                            $('#getResultDiv').append("<h5>No resource!</h5>")
                        }
                        let res = "<table class=\"table\">\n" +
                            "                    <thead>\n" +
                            "                    <tr>\n" +
                            "                        <th scope=\"col\">Account Username</th>\n" +
                            "                        <th scope=\"col\">Action</th>\n" +
                            "                        <th scope=\"col\">Listing Title</th>\n" +
                            "                        <th scope=\"col\">Time</th>\n" +
                            "                    </tr>\n" +
                            "                    </thead>\n" +
                            "                    <tbody>\n" +
                            "\n";
                        $('#getResultDiv').empty();
                        $.each(result.data,
                            function (i, log) {
                                res += "<tr>"
                                    + "<td>" + log.account + "</td>"
                                    + "<td>" + log.action + "</td>"
                                    + "<td>" + log.listingTitle + "</td>"
                                    + "<td>" + log.createdOn + "</td>"
                                    + "</tr>";
                            });
                        res += "</tbody></table>";
                        $('#getResultDiv').append(
                            res)
                        console.log("Success: ", result);
                    } else {
                        $("#getResultDiv").html("<strong>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error: function (e) {
                    $("#getResultDiv").html("<strong>Error</strong>");
                    console.log("ERROR DKSODLS: ", e);
                }
            });
        }
    })
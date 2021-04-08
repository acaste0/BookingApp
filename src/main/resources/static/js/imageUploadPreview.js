$(function() {
    // Multiple images preview in browser
    const imagesPreview = function (input, placeToInsertImagePreview) {

        if (input.files) {
            const filesAmount = input.files.length;

            for (i = 0; i < filesAmount; i++) {
                const reader = new FileReader();

                reader.onload = function (event) {
                    $($.parseHTML('<img>')).attr('src', event.target.result)
                        .attr('width', 150, 'height', 150)
                        .appendTo(placeToInsertImagePreview);
                }

                reader.readAsDataURL(input.files[i]);
            }
        }

    };

    $('#gallery-photo-add').on('change', function() {
        imagesPreview(this, 'div.gallery');
    });
});
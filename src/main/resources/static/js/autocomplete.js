$(document).ready(function () {
    var storeProducts = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/api/autocomplete/storeproduct/%QUERY',
            wildcard: '%QUERY'
        }
    });

    var stores = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/api/autocomplete/store/%QUERY',
            wildcard: '%QUERY'
        }
    });

    /*Description trimming helper function 3shan t7ot ... law el desc tawel */
    Handlebars.registerHelper('desc', function(passedString, startstring, endstring) {
        if(passedString){
            if(!startstring) startstring=0;
            if(!endstring) endstring=30;
            var theString = passedString.substring( startstring, endstring );
            if(theString.length >= endstring)
                theString +='...';
            return new Handlebars.SafeString(theString);
        }
    });

    $('#remote .typeahead').typeahead(
        {
            minLenght: 1,
            source: storeProducts,
            highlight: true
        }, {
            name: 'storeproducts',
            display: 'name',
            source: storeProducts,
            templates: {
                header: '<div class="container-fluid"><h6>Products</h6><hr style="margin: 0" /></div>',
                suggestion: Handlebars.compile('<div class="container-fluid"><div class="wrapper row"><div class="nopadding text-center col-md-1"><img src="http://placehold.it/50x50" alt="..." class="rounded img-fluid"/></div> <div class="col-lg-11 suggestion-header"><h6>{{name}}</h6><h6 class="text-muted">{{desc description 0 100}}</h6><h6 class="text-muted"><small><i class="fas fa-dollar-sign"></i>{{price}}</small></h6></div></div></div>')
            }
        }, {
            name: 'stores',
            display: 'name',
            source: stores,
            templates: {
                header: '<div class="container-fluid"><h6>Stores</h6> <hr style="margin: 0" /> </div> ',
                suggestion: Handlebars.compile('<div class="container-fluid"><h6>{{name}}</h6></div>')
            }
        }
    );

    $('.typeahead').bind('typeahead:select', function(ev, item) {
        if(item.hasOwnProperty("price"))
            window.location.href = '/store/products/' + item.id;
        else
            window.location.href = '/store/view/' + item.id;
    });

});
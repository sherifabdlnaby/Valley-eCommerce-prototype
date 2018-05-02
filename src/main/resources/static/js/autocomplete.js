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
                pending: '<div style="margin-top: 10px;" class="container-fluid text-muted"><h6><i class="fas fa-spin fa-circle-notch"></i> Searching... </h6></div>',
                notFound: '<div style="margin-top: 10px;" class="container-fluid text-muted"><h6><i class="fas fa-exclamation-circle"></i> No results found for products.</h6></div>',
                header: '<div style="margin-top: 10px;" class="container-fluid text-muted"><h6><i class="fas fa-search"></i> Products</h6><hr style="margin-top: 0; margin-bottom: 5px;" /></div>',
                suggestion: Handlebars.compile('<div class="container-fluid"><div class="wrapper row"><div class="nopadding text-center col-sm-1"><img src="/img/50x50.png" alt="..." class="rounded img-fluid"/></div> <div style="overflow: hidden;" class="col-sm-11 suggestion-header"><h6 style="margin-bottom: 0; margin-top: 3px; padding-bottom: 0;">{{name}}</h6><p style="margin-bottom: 0; margin-top: -6px;" class="text-muted"><small>{{desc description 0 100}}</small></p><h6 style="margin-top: -3px;" class="text-muted"><small><i class="fas fa-dollar-sign"></i>{{price}}</small></h6></div></div></div>')
            }
        }, {
            name: 'stores',
            display: 'name',
            source: stores,
            templates: {
                header: '<div style="margin-top: 10px;" class="container-fluid text-muted"><h6>Stores</h6> <hr style="margin-top: 0; margin-bottom: 5px;" /> </div> ',
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
/**
 * jQuery Go Menu
 * 
 * Based of the jQueryUI AutoComplete Widget
 */
$.widget("eve.GoMenu", $.ui.autocomplete, {
	_renderMenu: function(ul, items) {
		var self = this,
			currentCategory = "";
		
		$.each(items, function (index, item) {
			if (item.category != currentCategory) {
				ul.append('<li class="ui-autocomplete-category">' + item.category + '</li>');
				currentCategory=item.category;
			}
			
			self._renderItem(ul, item);
		})
	}
});
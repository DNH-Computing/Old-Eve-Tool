/**
 * 
 */
(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var self = this;
				
				// Hide the select box
				$(this).attr('disabled').hide();
				
				$(this).after('<input type="text" name="')
			}
		});
	})( jQuery );
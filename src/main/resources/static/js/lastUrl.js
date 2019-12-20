$.ajax({
	type: "HEAD",
	url: window.location.href,
	async: false,
	success: function(data, status, xhr) {
		var lastUrl = xhr.getResponseHeader("lastUrl");
		if (lastUrl != null) {
			window.location.replace(lastUrl);
		}
	}
});
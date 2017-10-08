<%@ attribute name="href" required="true"%>
<%@ attribute name="icon" required="true"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="description" required="true"%>

<div class="col-xs-12 col-md-6">
	<a href="${href}">
		<div class="panel panel-default">
			<div class="panel-body">
	
				<div class="row">
				
					<div class="col-xs-12 col-md-12">
						<div class="text-center"><i class="${icon}"></i></div>
					</div>
					
					<div class="col-xs-12 col-md-12">
						<h3 class="text-center">${title}</h3>
					</div>
					
					<div class="col-xs-12 col-md-12">
						<p class="text-justify">${description}</p>
					</div>
					
				</div>
			</div>
		</div>
	</a>
</div>
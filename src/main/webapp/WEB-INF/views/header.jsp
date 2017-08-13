<div class="container">
	<nav>
		<div class="btn-group" role="group">

			<div class="btn-group" role="group">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Model <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a
						href="${pageContext.request.contextPath }/modelDashboard">Dashboard</a></li>
					<li><a href="${pageContext.request.contextPath }/addModel">Add</a></li>
				</ul>
			</div>
			
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Component <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a
						href="${pageContext.request.contextPath }/componentDashboard">Dashboard</a></li>
					<li><a href="${pageContext.request.contextPath }/addComponent">Add</a></li>
				</ul>
			</div>
			
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Type <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a
						href="${pageContext.request.contextPath }/typeDashboard">Dashboard</a></li>
					<li><a href="${pageContext.request.contextPath }/addType">Add</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
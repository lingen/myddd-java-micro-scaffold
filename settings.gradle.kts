rootProject.name = "myddd-java-micro-scaffold"

include("document:document-domain")
include("document:document-api")
include("document:document-infra")
include("document:document-application")
include("document:document-bootstrap")

include("distributed-id:distributed-id-domain")
include("distributed-id:distributed-id-api")
include("distributed-id:distributed-id-infra")
include("distributed-id:distributed-id-application")
include("distributed-id:distributed-id-bootstrap")

include("rest:rest-bootstrap")
include("rest:rest-local-strategy")
include("rest:rest-micro-strategy")

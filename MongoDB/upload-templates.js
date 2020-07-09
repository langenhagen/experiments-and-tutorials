/*Generate Mongo db Documents for pt-templates into the db `c1_cre` into the collection
`sso_page_template`` on the given mongodb server.

Please review/change neighboring file `args.json` for template document specifications.

Usage:
  mongo mongodb://news-stage-db1:32000/ upload-templates.js
*/

enable_checks = true
conn = db.getMongo();
db = conn.getDB("c1_cre");
if (enable_checks && conn.getDBNames().indexOf(db.toString()) == -1) {
    print("Error: Database `c1_cre` doesn't exist.");
    quit(1);
}
if (enable_checks && !db.runCommand("isMaster").ismaster) {
    print("Error: Is not master.");
    quit(2);
}

collection_name = "sso_page_template";
if (enable_checks && db.getCollectionNames().indexOf(collection_name) == -1) {
    print("Error: Collection `" + collection_name + "` does not exist.");
    quit(3);
}
if (enable_checks && db.getCollectionNames().indexOf("sso_service") == -1) {
    print("Error: Collection `sso_service` does not exist.");
    quit(4);
}

args = JSON.parse(cat("upload-templates-args.json"));
args.forEach(
    function(template_item) {
        template_name = template_item["name"];
        if (enable_checks && db.getCollection(collection_name)
                    .find({"name": template_name}).length() != 0) {
            print("Error: A template with the name: `" + template_name + "` exists already.");
            quit(5);
        }

        service_id = template_item["sso_service_id"];
        if (enable_checks && db.getCollection("sso_service")
                    .find({"service_id": service_id}).length() != 1) {
            print("Warning: Problem finding 1 service with id: " + service_id + ".");
        }
        service_document = db.getCollection('sso_service').findOne({"service_id": service_id});

        // insert a Document
        db[collection_name].insertOne({
            "name": template_name,
            "content": cat(template_item["path"]),
            "language": template_item["language"],
            "created_at": new ISODate(),
            "sso_service": service_document._id,
            "default": template_item["default"]
        });
    }
);

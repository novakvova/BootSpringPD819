
var gulp = require('gulp');
var rimraf = require('rimraf');
var merge = require('merge-stream');

var deps = {
    "bootstrap": {
        "dist/**/*": ""
    },
    "jquery": {
        "dist/*": ""
    }
};
gulp.task("clean", function (cb) {
    return rimraf("src/main/resources/static/lib", cb);
});

gulp.task("scripts", function () {

    var streams = [];

    for (var prop in deps) {
        console.log("Prepping Scripts for: " + prop);
        for (var itemProp in deps[prop]) {
            streams.push(gulp.src("node_modules/" + prop + "/" + itemProp)
                .pipe(gulp.dest("src/main/resources/static/lib/" + prop + "/" + deps[prop][itemProp])));
        }
    }

    return merge(streams);

});
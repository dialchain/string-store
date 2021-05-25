# String Store

Simple interface for storing and serving strings.

## Release

The project is setup to be released on [Sonatype OSSRH](https://oss.sonatype.org).

Main steps for the release:

```
# Set the version
mvn versions:set -DnewVersion=0.0.1

# Deploy
mvn clean deploy
```

Clean up

```
# remove the pomBackup file
mvn versions:commit

# back to previous version
mvn versions:revert
```
# spring-cli-app
cli application with spring-boot CommandLineRunner and commons-cli

```bash
usage: java -jar spring-cli-app-<version>.jar --username=<username>
            --password=<key> --year=<year>
 -h,--help           print this message
 -p,--password <p>   password combination for given username
 -u,--username <u>   registered username to access the application
 -y,--year <y>       year for which data is requested

# example
$ java -jar spring-cli-app.jar --username=foo --password=bar --year=2017
```

### TODO
- [x] May be its good idea to get rid of logging or use logging in such a way that it looks like System.out.println().
- [ ] find of better way of closing (cli) application from CommandLineRunner impl on some error condition - currently I am doing System.exit(1), there should be better way than this.

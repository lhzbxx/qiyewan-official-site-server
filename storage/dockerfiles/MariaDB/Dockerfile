FROM mariadb:latest

MAINTAINER lhzbxx <lhzbxx@gmail.com>

CMD ["mysqld"]

RUN { \
        echo '[mysqld]'; \
        echo 'character-set-server=utf8mb4'; \
        echo 'collation-server=utf8mb4_unicode_ci'; \
        echo '[client]'; \
        echo 'default-character-set=utf8mb4'; \
    } > /etc/mysql/conf.d/charset.cnf

EXPOSE 3306
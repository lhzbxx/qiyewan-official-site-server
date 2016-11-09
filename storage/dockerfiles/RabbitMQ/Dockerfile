FROM rabbitmq

MAINTAINER lhzbxx <lhzbxx@gmail.com>

ADD rabbitmq_delayed_message_exchange-0.0.1.ez $RABBITMQ_HOME/plugins/

#RUN set -x \
#	&& apt-get update && apt-get install -y --no-install-recommends wget \
#	&& wget -O $RABBITMQ_HOME/plugins/rabbitmq_delayed_message_exchange-0.0.1.ez http://www.rabbitmq.com/community-plugins/v3.6.x/rabbitmq_delayed_message_exchange-0.0.1.ez

RUN rabbitmq-plugins enable --offline rabbitmq_management

RUN rabbitmq-plugins enable --offline rabbitmq_delayed_message_exchange

EXPOSE 15671 15672
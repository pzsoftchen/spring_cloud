docker network create -d overlay --subnet=192.168.66.0/24 fengchaoli

#config
    docker service create --with-registry-auth --network fengchaoli \
    --name config --log-opt max-size=100m --log-opt max-file=3 \
    -e spring_env=native \
    registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/config:latest

#registry
docker service create --with-registry-auth --network fengchaoli \
--name registry --log-opt max-size=100m --log-opt max-file=3 \
-p 8367:80 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/registry:latest

#zipkin
docker service create --with-registry-auth --network fengchaoli \
--name zipkin --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/zipkin:latest

#rabbitmq
docker service create --with-registry-auth --network fengchaoli \
--name rabbitmq --log-opt max-size=100m --log-opt max-file=3 \
rabbitmq:latest

#monitor
docker service create --with-registry-auth --network fengchaoli \
--name monitor --log-opt max-size=100m --log-opt max-file=3 \
-p 8369:80 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/monitor:latest

#geteway
docker service create --with-registry-auth --network fengchaoli \
--name gateway --log-opt max-size=100m --log-opt max-file=3 \
-e spring_env=test \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/gateway:latest

#ucenter
docker service create --with-registry-auth --network fengchaoli \
--name ucenter --log-opt max-size=100m --log-opt max-file=3 \
-e spring_env=test \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/ucenter:latest


#ocenter
docker service create --with-registry-auth --network fengchaoli \
--name ocenter --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/ocenter:latest

#sidecar
docker service create --with-registry-auth --network fengchaoli \
--name sidecar --log-opt max-size=100m --log-opt max-file=3 \
-e spring_env=test \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/sidecar:latest


#monitor
docker service create --with-registry-auth --network fengchaoli \
--name monitor --log-opt max-size=100m --log-opt max-file=3 \
-p 8761:80 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/monitor:latest
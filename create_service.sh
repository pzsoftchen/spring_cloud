docker network create -d overlay --subnet=192.168.66.0/24 fengchaoli

#config
docker service create --with-registry-auth --network fengchaoli \
--name config --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/config:latest

#registry
docker service create --with-registry-auth --network fengchaoli \
--name registry --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/registry:latest

#zipkin
docker service create --with-registry-auth --network fengchaoli \
--name zipkin --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/zipkin:latest

#monitor
docker service create --with-registry-auth --network fengchaoli \
--name monitor --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/monitor:latest

#geteway
docker service create --with-registry-auth --network fengchaoli \
--name gateway --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/gateway:latest

#ucenter
docker service create --with-registry-auth --network fengchaoli \
--name ucenter --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/ucenter:latest




#monitor
docker service create --with-registry-auth --network fengchaoli \
--name monitor --log-opt max-size=100m --log-opt max-file=3 \
-p 8761:80 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/monitor:latest
docker network create -d overlay --subnet=192.168.66.0/24 springcloud

#discovery
docker service create --with-registry-auth --network springcloud \
--name discovery --log-opt max-size=100m --log-opt max-file=3 \
-p 8761:80 \
registry.cn-shenzhen.aliyuncs.com/nothing/nothing-discovery:1.0


#chain
docker service create --with-registry-auth --network springcloud \
--name chain --log-opt max-size=100m --log-opt max-file=3 \
-p 8411:80 \
registry.cn-shenzhen.aliyuncs.com/nothing/nothing-chain:1.0


#geteway
docker service create --with-registry-auth --network fengchaoli \
--name gateway --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/gateway:1.0


#ucenter
docker service create --with-registry-auth --network fengchaoli \
--name ucenter --log-opt max-size=100m --log-opt max-file=3 \
registry-internal.cn-shenzhen.aliyuncs.com/dev_new_fengchaoli/ucenter:1.0


#backend
docker service create --with-registry-auth --network springcloud \
--name backend --log-opt max-size=100m --log-opt max-file=3 \
registry.cn-shenzhen.aliyuncs.com/nothing/nothing-backend:1.0

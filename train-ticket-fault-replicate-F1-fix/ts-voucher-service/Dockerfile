FROM python:3


RUN mkdir -p /app
WORKDIR /app

COPY requirements.txt ./
# RUN pip config set global.index-url https://mirrors.aliyun.com/pypi/simple/
RUN pip install --no-cache-dir -r requirements.txt

ADD ./server.py /app/
CMD [ "python", "server.py" ]

EXPOSE 16101
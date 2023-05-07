class FloatWindow {
	constructor() {
		this.element = null;
		this.isDragging = false;
		this.dragStartX = null;
		this.dragStartY = null;
		this.dragOffsetX = null;
		this.dragOffsetY = null;
		this.startTime = null;
		this.timer = null;

		// 定义 CSS 样式
		this.styles = `
			#float-window {
				position: fixed;
				top: 90%;
				left: 95%;
				width: 80px;
				height: 80px;
				background-color: #f8f8f8;
				border: 0.5px solid #7b7b7b;
				border-radius: 4px;
				z-index: 9999;
				display: none;
			}

			#float-window .close {
				position: absolute;
				top: 5px;
				right: 5px;
				width: 16px;
				height: 16px;
				background-color: transparent;
				border: none;
				text-align: center;
				line-height: 16px;
				cursor: pointer;
			}

			#float-window .clock-icon {
				display: block;
				margin: 8px auto 0;
				width: 32px;
				height: 32px;
				background-image: url("clock.png");
				background-repeat: no-repeat;
				background-position: center;
				background-size: contain;
			}

			#float-window .time {
				font-size: 14px;
				font-weight: bold;
				color: #8b8b8b;
				text-align: center;
				margin-top: 8px;
			}
		`;
	}

	create() {
		// 创建浮窗元素
		this.element = document.createElement('div');
		this.element.innerHTML = `
			<div class="close">×</div>
			<div class="clock-icon"></div>
			<div class="time">00:00</div>
		`;
		this.element.id = 'float-window';
		document.body.appendChild(this.element);

		// 添加样式
		const styleElement = document.createElement('style');
		styleElement.innerHTML = this.styles;
		document.head.appendChild(styleElement);

		// 绑定拖拽事件
		this.element.addEventListener('mousedown', this.startDrag.bind(this));
		this.element.addEventListener('mouseup', this.stopDrag.bind(this));

		// 绑定关闭按钮事件
		this.element.querySelector('.close').addEventListener('click', this.close.bind(this));
	}

	startDrag(e) {
		// 记录拖拽开始时的鼠标位置和浮窗位置
		this.isDragging = true;
		this.dragStartX = e.clientX;
		this.dragStartY = e.clientY;
		this.dragOffsetX = this.element.offsetLeft;
		this.dragOffsetY = this.element.offsetTop;

		// 绑定鼠标移动事件
		document.addEventListener('mousemove', this.drag.bind(this));
	}

	stopDrag() {
		// 解除鼠标移动事件绑定
		this.isDragging = false;
		document.removeEventListener('mousemove', this.drag);
	}

	drag(e) {
		if (this.isDragging) {
			// 计算鼠标移动的距离
			const deltaX = e.clientX - this.dragStartX;
			const deltaY = e.clientY - this.dragStartY;

			// 更新浮窗位置
			this.element.style.left = this.dragOffsetX + deltaX + 'px';
			this.element.style.top = this.dragOffsetY + deltaY + 'px';
		}
	}

	open() {
		// 显示浮窗
		this.element.style.display = 'block';

		// 记录打开时间并开始计时
		this.startTime = new Date().getTime();
		this.timer = setInterval(this.updateTime.bind(this), 1000);
	}

	close() {
		// 隐藏浮窗并停止计时
		this.element.style.display = 'none';
		clearInterval(this.timer);
	}

	updateTime() {
		// 计算经过的时间并更新时间显示
		const currentTime = new Date().getTime();
		const elapsedTime = currentTime - this.startTime;
		const seconds = Math.floor(elapsedTime / 1000);
		const minutes = Math.floor(seconds / 60);

		const timeString = `${("0" + minutes).slice(-2)}:${("0" + (seconds % 60)).slice(-2)}`;

		this.element.querySelector('.time').innerHTML = timeString;
	}

	getCurrentTime() {
		// 获取当前时间并计算经过的时间
		const currentTime = new Date().getTime();
		const elapsedTime = currentTime - this.startTime;

		// 将经过的时间转换为一个对象
		const timeObject = {
			elapsedTime: elapsedTime,
			seconds: Math.floor(elapsedTime / 1000),
			minutes: Math.floor(elapsedTime / 1000 / 60),
			hours: Math.floor(elapsedTime / 1000 / 60 / 60)
		};
		return timeObject;
	}

}
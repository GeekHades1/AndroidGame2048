# AndroidGame2048
Android端2048游戏的实现

# 关于2048游戏说明
这是20岁的Gabriele Cirulli开发的一款数字游戏。初衷就是觉得好玩，在将其开源版本放到Github上后，意外走红。
这款游戏的玩法很简单，每次可以选择上下左右滑动，每滑动一次，所有的数字方块都会往滑动的方向靠拢，
系统也会在空白的地方乱数出现一个数字方块，相同数字的方块在靠拢、相撞时会相加。不断的叠加最终拼凑出2048这个数字就算成功。

# 开发步骤

	1、自定义的网格布局。
		自定义一个类继承GridLayout。
		重写OnSizeChanged()方法来动态的计算屏幕大小，启动initView()方法
		initView()方法来作为初始化入口，initView需要获取列数，然后初始化Cards数组，创建初始视图
		重写onTouchEvent()方法，来监听屏幕滑动的方向，依次由moveleft(),moveRight(),moveUp(),moveDown()对应的触发事件
	2、自定义卡片类Card
		继承自FrameLayout。这也是程序的一个重点。
		需要为成员变量num（卡片上的数字）设置getter 和 setter方法
		将数字包装成为一个TextView然后addView()进FrameLayout中。
	
# 算法说明：
	
	1、随机点遍历算法：
		创建一个List<Point> 类型的emptyPoint变量来记录游戏界面存在的空点。
		然后利用Random % emptyPoint.size()来随机获取一个点的位置。生成卡片
	2、矩阵翻转算法，这里列举向上翻转，其他类似：
	private void moveUp() {
        Log.i(TAG, "up");
        checkFail();
        boolean merge = false;//合并标志
        //以行为遍历
        for (int x = 0; x < column;x++) {
            for (int y = 0; y < column;y++) {
                for (int y1 = y+1;y1<column;y1++) {
                    if (cards[y1][x].getNum() > 0) {
                        //说明不是空位置
                        if (cards[y][x].getNum() == 0) {
                            //如果目前为止是空位置，则交换两个位置
                            cards[y][x].setNum(cards[y1][x].getNum());
                            cards[y1][x].setNum(0);//设为空
                            y--;//如果是空位置交换必须在重新检测一次
                            merge = true;
                        } else if (cards[y][x].equals(cards[y1][x])) {
                            //加分
                            Message msg = new Message();
                            msg.what = ADD_SCORE;
                            msg.arg1 = cards[y][x].getNum();
                            mainHandler.sendMessage(msg);
                            //如果两个相同就合并
                            cards[y][x].setNum(cards[y1][x].getNum() * 2);
                            cards[y1][x].setNum(0);
                            merge = true;
                            playAudio(this.CONNECT_AD);
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomCard();
            checkFail();
        }
		

# 游戏失败条件
	当emptyPoint没有值的时候，并且每个卡片无法再和四周的卡片合并时，可以判定游戏失败。

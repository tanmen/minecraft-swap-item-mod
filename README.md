# minecraft-swap-item-mod

手に持っている道具が壊れたとき、インベントリ内にある同じアイテムを
自動で手に持ち替える Minecraft（Forge）mod。ツールが耐久切れで壊れるたびに
同じ道具を手動で持ち直す手間をなくす。

## 仕組み

`PlayerDestroyItemEvent`（アイテム破壊イベント）を購読し、壊れたアイテムと
同じアイテムをプレイヤーのインベントリから探して手に持ち替える
（`SwapItemMod.java`）。mod id: `swapitem`。

## 開発・ビルド

Minecraft Forge MDK ベース。

```sh
./gradlew genIntellijRuns   # IntelliJ 用 run 設定生成
./gradlew genEclipseRuns    # Eclipse 用
./gradlew build             # ビルド
```

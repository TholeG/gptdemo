use rand::Rng;
use std::time::Instant;

const ARRAY_SIZE: usize = 10_000_000;  // 10 Millionen

fn main() {
    println!("Erzeugen und sortieren eines großen Arrays...");

    let mut rng = rand::thread_rng();
    let mut array: Vec<i32> = (0..ARRAY_SIZE).map(|_| rng.gen()).collect();
    
    let start = Instant::now();

    quick_sort(&mut array, 0, ARRAY_SIZE - 1);

    let duration = start.elapsed();

    println!("Sortierung abgeschlossen in {} Millisekunden.", duration.as_millis());
}

fn quick_sort(arr: &mut [i32], low: usize, high: usize) {
    if high > low {
        let pi = partition(arr, low, high);

        if pi > 0 { quick_sort(arr, low, pi - 1); }
        quick_sort(arr, pi + 1, high);
    }
}

fn partition(arr: &mut [i32], low: usize, high: usize) -> usize {
    let pivot = arr[high];
    let mut i = low;
    let mut j = low;

    while j < high {
        if arr[j] <= pivot {
            arr.swap(i, j);
            i += 1;
        }
        j += 1;
    }

    arr.swap(i, high);

    return i;
}


